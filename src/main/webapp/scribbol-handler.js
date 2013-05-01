var scribbolnet = scribbolnet || { version: "0.1.0" };

/**
 * Event Handler
 * @param eventName
 * @param event
 */
function handleEvent(eventName, event) {
    if( eventName == 'object:moving') {
        handleObjectMove(event);
    }
    else if( eventName == 'object:scaling') {
        handleObjectScale(event);
    }
    else if( eventName == 'object:rotating') {
        handleObjectRotate(event);
    }
    else if( eventName == 'path:created') { // stroke, strokeWidth, path, currentHeight, currentWidth, left, top
        handlePathCreated(event);
    }
    else if ( eventName == 'object:created') {
        var object = event.object;
        var id = scribbol.util.generateNewId();
        scribbol.util.registerObject(object, id);
        scribbol.util.log('object created: ' + id);
    }
    else if ( eventName == 'selection:created') {
        scribbol.util.log('selection created');
    }
    else if ( eventName == 'object:selected' ) {
        var object = event.target;
        id = scribbol.util.getObjectId(object);
        scribbol.util.log('object:selected:' +id);
    }
}

function observe(eventName) {
    scribbol.canvas.on(eventName, function(e){ scribbol.event.handleEvent(eventName, e) });
}

/**
 *
 * @param event
 */
function handleObjectMove(event) {
    var docId = scribbol.getDocument();
    var target = event.target;
    var ids = []
    var lefts = [];
    var tops = [];

    // Handle single selected
    if(!target.objects) {
        ids.push(scribbol.util.getObjectId(target));
        lefts.push(target.left);
        tops.push(target.top);
    }
    // Handle multiple selected
    else {
        var deltaLeft =  target.left - target.originalState.left;
        var deltaTop = target.top - target.originalState.top;
        target.objects.forEach(function(obj){
            ids.push(scribbol.util.getObjectId(obj));
            lefts.push(obj.originalLeft + deltaLeft);
            tops.push(obj.originalTop + deltaTop);
        })
    }

    // Send the message
    var message = {
        "command" : "draw",
        "drawCommand" : "object:moving",
        "docId": "default",
        "objId": ids.toString(),
        "left": lefts.toString(),
        "top": tops.toString()
    };
    scribbol.util.log('object moving: ' + ids.toString()) ;
    scribbol.net.sendMessage(message);
}

/**
 *
 * @param event
 */
function handleObjectScale(event) {
    var docId = scribbol.getDocument();
    var target = event.target;
    var ids = []
    var scaleX = [];
    var scaleY = [];

    // Handle single selected
    if(!target.objects) {
        ids.push(scribbol.util.getObjectId(target));
        scaleX.push(target.scaleX);
        scaleY.push(target.scaleY);
    }
    // Handle multiple selected
    else {
        var deltaLeft =  target.left - target.originalState.left;
        var deltaTop = target.top - target.originalState.top;
        target.objects.forEach(function(obj){
            ids.push(scribbol.util.getObjectId(obj));
            scaleX.push(obj.originalLeft + deltaLeft);
            scaleY.push(obj.originalTop + deltaTop);
        })
    }

    var message = {
        "command" : "draw",
        "drawCommand" : "object:scaling",
        "docId": "default",
        "objId": ids.toString(),
        "scaleX": scaleX.toString(),
        "scaleY": scaleY.toString()
    };
    scribbol.util.log('object rotating: ' + ids.toString()) ;
    scribbol.net.sendMessage(message);
}

/**
 *
 * @param event
 */
function handleObjectRotate(event) {
    var object = event.target;
    var id = scribbol.util.getObjectId(object);
    var docId = scribbol.getDocument();

    var message = {
        "command" : "draw",
        "drawCommand" : "object:rotating",
        "docId": "default",
        "objId": id,
        "angle": object.angle
    };
    scribbol.util.log('object rotating: ' + id) ;
    scribbol.net.sendMessage(message);
}

/**
 *
 * @param event
 */
function handlePathCreated(event) {
    var object = event.path;
    var id = scribbol.util.generateNewId().toString();
    var docId = scribbol.getDocument();

    var message = {
        "command" : "draw",
        "drawCommand" : "path:created",
        "docId": "default",
        "objId": id,
        "width": object.width,
        "height": object.height,
        "left": object.left,
        "top": object.top,
        "stroke": object.stroke.toString(),
        "strokeWidth": object.strokeWidth,
        "path": object.path.toString(),
        "svg": object.toSVG(),
        "fill": object.fill
    };
    scribbol.util.registerObject(object, id);
    scribbol.util.log('path created: ' + id);
    scribbol.net.sendMessage(message);
}

scribbol.event.handleEvent = handleEvent;
scribbol.event.observe = observe;