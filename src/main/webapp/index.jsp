<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <script src="https://raw.github.com/kangax/fabric.js/master/dist/all.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/org/cometd.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.cometd.js"></script>
    <script type="text/javascript" src="application.js"></script>
    <%--
    The reason to use a JSP is that it is very easy to obtain server-side configuration
    information (such as the contextPath) and pass it to the JavaScript environment on the client.
    --%>
    <script type="text/javascript">
        var config = {
            contextPath: '${pageContext.request.contextPath}'
        };
    </script>
</head>
<body>
    <style>
        p { margin-top: 0px; margin-bottom: 0px; }
        #log1, #body { display: inline-block; width: 350px; height: 100px; overflow-y: scroll; font-family: monospace; font-size: 11px; }
        .log strong { margin-bottom: 10px; display: block; }
    </style>
    <div>
        <div id="canvasContainer">
            <canvas id="c" width=700 height=900></canvas>
        </div>
        <div id="toolbar">
            <p>
                <button type="button" class="btn mode" onclick="toggleMode()">Mode</button>
                <button type="button" class="btn rect">Rectangle</button>
                <button type="button" class="btn circle">Circle</button>
                <button type="button" class="btn triangle">Triangle</button>
                <button type="button" class="btn freehand">Freehand</button>
            </p>
        </div>
    </div>

    <div>
        <div id="log1">&nbsp;</div>
        <div id="body"></div>
    </div>
    <script>

        var canvas = new fabric.Canvas('c');

        // create a rectangle object
        var rect = new fabric.Rect({
            left: 100,
            top: 100,
            fill: 'red',
            width: 20,
            height: 20
        });

        // "add" rectangle onto canvas
        canvas.add(rect);
        canvas.isDrawingMode = true;

        var log1 = document.getElementById('log1');

        function log(message) {
            var el = document.createElement('p');
            el.appendChild(document.createTextNode(message));
            var containerEl = log1;
            containerEl.insertBefore(el, containerEl.firstChild);
        }

        function toggleMode() {
            canvas.isDrawingMode = !canvas.isDrawingMode;
        }

        function handleEvent(eventName, event) {
            if( eventName == 'object:moving') {
                log('object moving!!!!') ;
            }
            log(eventName);
        }


        function observe(eventName) {
            canvas.on(eventName, function(e){ handleEvent(eventName, e) });
        }

        observe('object:modified');
        observe('object:selected');
        observe('object:moving');
        observe('object:scaling');
        observe('object:rotating');
        observe('before:selection:cleared');
        observe('selection:cleared');
        observe('selection:created');
        observe('mouse:up');
        observe('mouse:down');
        observe('mouse:move');
        observe('after:render');
        observe('path:created');
        observe('object:added');

    </script>

</body>
</html>
