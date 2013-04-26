var scribbolnet = scribbolnet || { version: "0.1.0" };

scribbolnet.cometd = jQuery.cometd;
scribbolnet.channel = '/scribbol';


function sendCometdMessage(message) {
    message.clientId = scribbolnet.cometd.getClientId();
    message.name = scribbolnet.cometd.getName();
    scribbol.util.log("SEND: " + JSON.stringify(message));
    scribbolnet.cometd.publish(scribbolnet.channel, message);

}

function receiveCometDMessage(message) {
    scribbol.util.log("RECV: " + JSON.stringify(message));
    if(message.clientId != scribbolnet.cometd.getClientId()) {
        scribbol.handleMessage(message);
    }
}

scribbol.net.sendMessage = sendCometdMessage;
scribbol.net.receiveMessage = receiveCometDMessage;