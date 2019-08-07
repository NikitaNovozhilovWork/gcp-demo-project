'use strict';

// Imports the Google Pub Sub
const {PubSub} = require('@google-cloud/pubsub');

// Instantiates a pub sub client
const pubsub = new PubSub();

// Buffer
const Buffer = require('safe-buffer').Buffer;

// Imports the Google Cloud client library
const {Spanner} = require('@google-cloud/spanner');

// Instantiates a client
const spanner = new Spanner();

// Your Cloud Spanner instance ID
const instanceId = 'gcp-demo-spanner';

// Your Cloud Spanner database ID
const databaseId = 'chat-messages';

exports.subscribe = (receivedMessage, context) => {
	try {
      var message = JSON.parse(Buffer.from(receivedMessage.data, 'base64').toString());
      console.log('message received: ', message);

	  // Gets a reference to a Cloud Spanner instance and database
      const instance = spanner.instance(instanceId);
      const database = instance.database(databaseId);

      database.runTransaction(async (err, transaction) => {
      	  if (err) {
      		console.error(err);
      		return;
      	  }
      	  try {
      		const [rowCount] = await transaction.runUpdate({
      		  sql:
      			'INSERT messages (messageId, sent, text, translation, userDisplayName, userId, userUsername) VALUES (@messageId, @sent, @text, @translation, @userDisplayName, @userId, @userUsername)',
      		  params: {
      			messageId: message.messageId,
      			sent: new Date(message.sent),
      			text: message.text,
      			translation: message.translation,
      			userDisplayName: message.userDisplayName,
      			userId: message.userId,
      			userUsername: message.userUsername,
      		  },
      		});

      		console.log(
      		  `Successfully inserted ${rowCount} record into the messages table.`
      		);

      		await transaction.commit();
      	  } catch (err) {
      		console.error('ERROR:', err);
      	  } finally {
      		// Close the database when finished.
      		database.close();
      	  }
      	});
    } catch (e) {
      console.error('PubSub message was not JSON', e);
    }
};