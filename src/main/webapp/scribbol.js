/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/7/13
 * Time: 4:06 PM
 * To change this template use File | Settings | File Templates.
 */

var scribbol = scribbol || { version: "0.1.0" };

scribbol.initialize = function(canvas, logContainer) {
    scribbol.canvas = canvas;
    scribbol.logContainer = logContainer;
    scribbol.__defaultId = 0;

    // object to id map
    scribbol.id2ObjMap = new Array();

    scribbol.event.observe('object:modified');
    scribbol.event.observe('object:selected');
    scribbol.event.observe('object:moving');
    scribbol.event.observe('object:scaling');
    scribbol.event.observe('object:rotating');
    scribbol.event.observe('before:selection:cleared');
    scribbol.event.observe('selection:cleared');
    scribbol.event.observe('selection:created');
    scribbol.event.observe('mouse:up');
    scribbol.event.observe('mouse:down');
    scribbol.event.observe('mouse:move');
    scribbol.event.observe('after:render');
    scribbol.event.observe('path:created');
    scribbol.event.observe('object:added');
};


scribbol.setDocument = function(documentName) {
    scribbol.document = documentName;
}

scribbol.getDocument = function() {
    return scribbol.document;
}

scribbol.util = { };
scribbol.event = { };
scribbol.draw = { };

/**
 * Log a message to the log container
 *
 * @param message
 */
scribbol.util.log = function(message) {
    var el = document.createElement('p');
    el.appendChild(document.createTextNode(message));
    var containerEl = scribbol.logContainer;
    containerEl.insertBefore(el, containerEl.firstChild);
}

/**
 * Add an object to the canvas and register it to the id specified.
 *
 * @param object
 * @param id
 */
function addObject(object, id) {
    scribbol.canvas.add(object);
    scribbol.util.registerObject(object, id);
    scribbol.canvas.renderAll();
}

/**
 * Generate a unique ID using SHA-1
 *
 * @return {Number}
 */
function generateNewId() {
    return ++scribbol.__defaultId;
}

/**
 * Register a Canvas object with scribbol
 *
 * @param object
 * @param id
 */
function registerObject(object, id) {
    object.SID = id;
    scribbol.id2ObjMap[id] = object;
    scribbol.util.log("Registered object" + id);
}

/**
 *
 * @param id
 * @return {*}
 */
function getObject(id) {
    return scribbol.id2ObjMap[id];
}

/**
 * Remove a specific object from the map.
 *
 * @param object
 */
function unregisterObject(object) {
    var id = getObjectId(object);
    if(object == null || id == null) {
        scribbol.util.unregisterObjectId(id);
    }
}

/**
 * Remove a object from the map
 *
 * @param id
 */
function unregisterObjectId(id) {
    delete scribbol.id2ObjMap[id];
}

/**
 * Extract the ID from a Canvas object
 *
 * @param object
 * @return {*}
 */
function getObjectId(object) {
    var id = object.SID;
    return id;
}

scribbol.util.generateNewId = generateNewId;
scribbol.util.registerObject = registerObject;
scribbol.util.getObjectId = getObjectId;
scribbol.util.getObject = getObject;
scribbol.util.unregisterObject = unregisterObject;
scribbol.util.unregisterObjectId = unregisterObjectId;
scribbol.util.addObject = addObject;

function toggleMode() {
    scribbol.canvas.isDrawingMode = !scribbol.canvas.isDrawingMode;
}

function setSelectMode() {
    scribbol.canvas.isDrawingMode = false;
}

function setFreehandMode() {
    scribbol.canvas.isDrawingMode = true;
}

function newRectangle() {
    scribbol.canvas.isDrawingMode = false;
    var rect = scribbol.draw.createDefaultRectangle();
    scribbol.draw.addNewObject(rect);

}

function newEllipse() {
    scribbol.canvas.isDrawingMode = false;
    var rect = scribbol.draw.createDefaultEllipse();
    scribbol.draw.addNewObject(rect);

}

function setDrawingMode(bool) {
    scribbol.canvas.isDrawingMode = bool;
}

scribbol.toggleMode = toggleMode;
scribbol.setFreehandMode = setFreehandMode;
scribbol.setSelectMode = setSelectMode;
scribbol.setDrawingMode = setDrawingMode;
scribbol.newRectangle = newRectangle;
scribbol.newEllipse = newEllipse;


scribbol.net = {};

function dummySendMessage(message) {
    scribbol.util.log("DUMMY SEND: " + JSON.stringify(message));
}

function dummyReceiveMessage(message) {
    scribbol.util.log("DUMMY RECEIVE: " + JSON.stringify(message));
}

scribbol.net.sendMessage = dummySendMessage;
scribbol.net.receiveMessage = dummyReceiveMessage;


function dummyHandleMessage(message) {
    scribbol.util.log("DUMMY HANDLE MESSAGE: " + JSON.stringify(message));
}

scribbol.handleMessage = dummyHandleMessage;








