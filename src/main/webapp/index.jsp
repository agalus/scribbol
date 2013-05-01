<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <script src="https://raw.github.com/kangax/fabric.js/master/dist/all.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/org/cometd.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.cometd.js"></script>
    <script type="text/javascript" src="scribbol.js"></script>
    <script type="text/javascript" src="scribbol-net.js"></script>
    <script type="text/javascript" src="scribbol-handler.js"></script>
    <script type="text/javascript" src="scribbol-message.js"></script>
    <script type="text/javascript" src="scribbol-draw.js"></script>
    <script type="text/javascript" src="sha1.js"></script>
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
        #logContainer, #body { display: inline-block; width: 350px; height: 100px; overflow-y: scroll; font-family: monospace; font-size: 11px; overflow-y: scroll }
        #debugContainer { position: absolute; display: inline-block; bottom: 20px; left: 0px; height: 100px; font-family: monospace; font-size: 11px; }
        .log strong { margin-bottom: 10px; display: block; }
    </style>
    <div>
        <div id="toolbar">
            <p>
                <button type="button" class="btn mode" onclick="toggleMode()">Freehand / Sel</button>
                <button type="button" class="btn mode" onclick="scribbol.draw.selectNone()">Select None</button>
                <button type="button" class="btn rect" onclick="scribbol.newRectangle()">Rectangle</button>
                <button type="button" class="btn circle" onclick="scribbol.newEllipse()">Circle</button>
                <button type="button" class="btn circle" onclick="scribbol.draw.drawText('test')">Text</button>
                <button type="button" class="btn debug" onclick="scribbol.draw.deleteSelected()">Delete Selected</button>
                <button type="button" class="btn debug" onclick="toggle_visibility('debugContainer')">Debug</button>
            </p>
            <p>
                Fill Color:<input type="color" id="frmFillColor" value="#FF0000"/>
                Stroke Color:<input type="color" id="frmStrokeColor" value="#000000"/>
                Stroke Width:<input type="number" id="frmStrokeWidth" value="5"/>
                <button type="button" class="btn debug" onclick="applyStyle()">Apply</button>
            </p>
        </div>
        <div id="canvasContainer">
            <canvas id="canvasElement" width=200 height=200></canvas>
        </div>
    </div>

    <div id="debugContainer" style="display:none;">
        <div id="logContainer">&nbsp;</div>
        <div id="body">&nbsp;</div>
    </div>
    <script>

        function positionElements(canvasElement, toolbarContainer, debugContainer) {
            canvasElement.width = document.width;
            canvasElement.height = document.height - toolbarContainer.style.pixelHeight;

        }

        function toggle_visibility(id) {
            var e = document.getElementById(id);
            if(e.style.display == 'block')
                e.style.display = 'none';
            else
                e.style.display = 'block';
        }

        function generateId() {
            var i = Math.random();
            var hash = CryptoJS.SHA1("scribbol" +i);
            return hash;
        }

        function applyStyle() {
            var style = {
                strokeColor : document.getElementById("frmStrokeColor").value,
                strokeWidth : document.getElementById("frmStrokeWidth").value,
                fillColor : document.getElementById("frmFillColor").value
            };
            scribbol.draw.setObjectStyleSelected(style);

        }


        var log = document.getElementById('logContainer');
        var debugContainer = document.getElementById('debugContainer');
        var canvasElement = document.getElementById('canvasElement');
        var toolBar = document.getElementById('toolbar');

        positionElements(canvasElement,toolBar, debugContainer);



        // Setup the Fabric Canvas
        var canvas = new fabric.Canvas('canvasElement');

        scribbol.initialize(canvas, log);
        scribbol.util.generateNewId = generateId;
        scribbol.setDrawingMode(true);

    </script>

</body>
</html>
