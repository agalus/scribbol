var scribbolnet = scribbolnet || { version: "0.1.0" };

scribbolnet.cometd = jQuery.cometd;
scribbolnet.channel = '/service/message';


function sendCometdMessage(message) {
    scribbol.util.log("SEND: " + JSON.stringify(message));
    scribbolnet.cometd.publish(scribbolnet.channel, message);

}

function receiveCometDMessage(message) {
    scribbol.util.log("RECV: " + JSON.stringify(message));

}

scribbol.net.sendMessage = sendCometdMessage;
scribbol.net.receiveMessage = receiveCometDMessage;