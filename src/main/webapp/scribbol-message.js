var scribbolnet = scribbolnet || { version: "0.1.0" };

function handleMessage(message) {
    scribbol.util.log("DUMMY RECEIVE: " + JSON.stringify(message));
    if(message.command == 'draw') {
        handleDrawCommand(message);
    }
}

function handleDrawCommand(message) {
    scribbol.util.log("handling draw command " + JSON.stringify(message));
    var drawCommand = message.drawCommand
    if(message.drawCommand == 'path:created') {
        var obj = new fabric.Path(message.path);

        obj.width = message.width;
        obj.height = message.height;
        obj.left = message.left;
        obj.top = message.top;
        obj.strokeWidth = message.strokeWidth;
        obj.stroke = message.stroke;
        obj.fill = message.fill;

        var objId = message.objId
        scribbol.util.registerObject(obj, objId);
        scribbol.canvas.add(obj);
    }

    else if(message.drawCommand == 'object:moving') {
        var objIds = message.objId.split(",");
        var lefts = message.left.split(",");
        var tops = message.top.split(",");

        for(var i=0; i<objIds.length; i++) {
            var objId = objIds[i];
            var obj = scribbol.util.getObject(objId);
            obj.left = lefts[i];
            obj.top = tops[i];
        }

        scribbol.canvas.renderAll();
    }

    else if(message.drawCommand == 'object:rotating') {
        var objId = message.objId;
        var obj = scribbol.util.getObject(objId);
        obj.angle = message.angle;
        scribbol.canvas.renderAll();
    }

    else if(message.drawCommand == 'object:scaling') {
        var objId = message.objId;
        var obj = scribbol.util.getObject(objId);
        obj.scaleX = message.scaleX;
        obj.scaleY = message.scaleY;
        scribbol.canvas.renderAll();
    }

    else if(message.drawCommand == 'object:deleted') {
        var idsString = message.ids;
        var ids = idsString.split(",");
        ids.forEach( function(id){
                var obj = scribbol.util.getObject(id);
                scribbol.canvas.remove(obj);
                scribbol.util.unregisterObject(obj);
            }
        )
    }

    else if(message.drawCommand == 'object:modified') {
        var objIds = message.objId.split(",");
        var fillColor = message.fillColor;
        var strokeColor = message.strokeColor;
        var strokeWidth = message.strokeWidth;

        for(var i=0; i<objIds.length; i++) {
            var objId = objIds[i];
            var obj = scribbol.util.getObject(objId);
            obj.stroke = strokeColor;
            obj.strokeWidth = strokeWidth;
            obj.fill = fillColor;
        }

        scribbol.canvas.renderAll();
    }

    else if(message.drawCommand == 'object:created') {
        var objId = message.objId;
        var obj;
        if(message.type == 'rect') {
            obj = new fabric.Rect({
                left: message.left,
                top: message.top,
                fill: message.fill,
                width: message.width,
                height: message.height
            });

        } else if(message.type == 'circle') {
            obj =   new fabric.Circle({
                left: message.left,
                top: message.top,
                radius: message.radius,
                fill: message.fill
            });
        } else if(message.type == 'text') {
            obj =   new fabric.Text(message.text, {
                left: message.left,
                top: message.top,
                fill: message.fill,
                fontSize: message.fontSize,
                textAlign: message.textAlign
            });
        }

        else {
            return;
        }
        scribbol.util.registerObject(obj, objId);
        scribbol.canvas.add(obj);


    }

}

scribbol.handleMessage = handleMessage;