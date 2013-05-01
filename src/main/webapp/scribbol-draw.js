var scribbolnet = scribbolnet || { version: "0.1.0" };

function createDefaultRectangle() {
    var rect = new fabric.Rect({
        left: 100,
        top: 100,
        fill: 'green',
        width: 20,
        height: 20
    });
    return rect;
}
function createDefaultEllipse() {
    var circle = new fabric.Circle({
        left: 100,
        top: 100,
        radius: 50,
        fill: 'red'
    });
    return circle;

}
function addNewObject(object) {
    var id = scribbol.util.generateNewId().toString();
    scribbol.canvas.add(object);
    scribbol.util.registerObject(object, id);
    scribbol.canvas.renderAll();

    var docId = scribbol.getDocument();

    var message = {
        "command" : "draw",
        "drawCommand" : "object:created",
        "docId": "default",
        "objId": id,
        "type" : object.type,
        "width": object.width,
        "height": object.height,
        "left": object.left,
        "top": object.top,
        "stroke": object.stroke,
        "strokeWidth": object.strokeWidth,
        "svg": object.toSVG(),
        "fill": object.fill,
        "radius" : object.radius,
        "fontSize" : object.fontSize,
        "textAlign" : object.textAlign,
        "text" : object.text
    };
    scribbol.util.log('object created: ' + id);
    scribbol.net.sendMessage(message);

}
scribbol.draw.createDefaultEllipse = createDefaultEllipse;
scribbol.draw.createDefaultRectangle = createDefaultRectangle;
scribbol.draw.addNewObject = addNewObject;


/**
 * Delete the selected object(s) of a canvas.
 */
function deleteSelected()
{
    var ids = [];
    // Delete all selected objects
    if(scribbol.canvas.getActiveGroup()){
        scribbol.canvas.getActiveGroup().forEachObject(function(o){
            canvas.remove(o);
            ids.push(scribbol.util.getObjectId(o));
        });
        scribbol.canvas.discardActiveGroup().renderAll();
    }

    // Delete a single selected object
    else {
        var obj = canvas.getActiveObject();
        ids.push(scribbol.util.getObjectId(obj));
        scribbol.canvas.remove(obj);
    }

    // Send message to server
    if(ids.length > 0) {
        var message = {
            "command" : "draw",
            "drawCommand" : "object:deleted",
            "ids" : ids.toString()
        };
        scribbol.util.log('object deleted: ' + ids.toString());
        scribbol.net.sendMessage(message);
    }
}

/**
 *
 * @param width
 */
function setObjectStyleSelected(style) {
    var ids = [];
    var fillColor = style.fillColor;
    var strokeColor = style.strokeColor;
    var strokeWidth = style.strokeWidth;

    // Apply style to selected objects
    if(scribbol.canvas.getActiveGroup()){
        scribbol.canvas.getActiveGroup().forEachObject(function(o){
            ids.push(scribbol.util.getObjectId(o));
            o.stroke = strokeColor;
            o.strokeWidth = strokeWidth;
            o.fill = fillColor
        });
    }

    // Apply style to single selected object
    else {
        var obj = canvas.getActiveObject();
        ids.push(scribbol.util.getObjectId(obj));
        obj.stroke = strokeColor;
        obj.strokeWidth = strokeWidth;
        obj.fill = fillColor;
    }
    scribbol.canvas.renderAll();

    // Send message to server
    if(ids.length > 0) {
        var message = {
            "command" : "draw",
            "drawCommand" : "object:modified",
            "objId" : ids.toString(),
            "fillColor" : fillColor,
            "strokeColor" : strokeColor,
            "strokeWidth" : strokeWidth
        };
        scribbol.util.log('object deleted: ' + ids.toString());
        scribbol.net.sendMessage(message);
    }

}

function selectNone() {
//    scribbol.canvas.setActiveGroup(null);
//    scribbol.canvas.setActiveObject(null);
//    scribbol.canvas.renderAll();
}

function drawText(text) {
    var object = new fabric.Text(text, {
        fontFamily: "Arial",
        left: 150,
        top: 100,
        fontSize: 24,
        textAlign: "left",
        fill: "#000000"
    });
    scribbol.canvas.add(object);
    scribbol.draw.addNewObject(object);
    scribbol.canvas.renderAll();
}


scribbol.draw.deleteSelected = deleteSelected;
scribbol.draw.setObjectStyleSelected = setObjectStyleSelected;
scribbol.draw.selectNone = selectNone;
scribbol.draw.drawText = drawText;