<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
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
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>

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

            <div class="btn-group">
                <button class="btn" id="menuAction"><span>Action</span></button>
                <button class="btn dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#" onclick="handleSelectTool()">Select</a></li>
                    <li><a href="#" onclick="handleFreehandTool()">Freehand</a></li>
                </ul>
            </div>

            <div class="btn-group">
                <button class="btn" onclick="scribbol.newRectangle()">Rectangle</button>
                <button class="btn" onclick="scribbol.newEllipse()">Ellipse</button>
                <%--<button class="btn" onclick="scribbol.draw.drawText('test')">Text</button>--%>
                <a href="#myModal" role="button" class="btn" data-toggle="modal">Text</a>
            </div>

            <!-- Text Modal -->
            <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="myModalLabel">Create Text</h3>
                </div>
                <div class="modal-body">
                    <input id="inputText" type="text" width="40" value="Enter text here."/>
                </div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                    <button class="btn btn-primary" data-dismiss="modal" onclick="handleCreateText()">Create</button>
                </div>
            </div>

            <div class="btn-group">
                <button class="btn" onclick="scribbol.draw.deleteSelected()">Delete</button>
                <button class="btn" onclick="toggle_visibility('debugContainer')">Debug</button>
            </div>


            <div class="btn-group">
                <button class="btn">Fill</button>
                <button class="btn" id="menuFillColor"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></button>
                <button class="btn dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#" onclick="handleFillColor('#000000')" style="background-color:#000000">Black</a></li>
                    <li><a href="#" onclick="handleFillColor('#575757')" style="background-color:#575757">Dk. Gray</a></li>
                    <li><a href="#" onclick="handleFillColor('#AD2323')" style="background-color:#AD2323">Red</a></li>
                    <li><a href="#" onclick="handleFillColor('#2A4BD7')" style="background-color:#2A4BD7">Blue</a></li>
                    <li><a href="#" onclick="handleFillColor('#1D6914')" style="background-color:#1D6914">Green</a></li>
                    <li><a href="#" onclick="handleFillColor('#814A19')" style="background-color:#814A19">Brown</a></li>
                    <li><a href="#" onclick="handleFillColor('#8126C0')" style="background-color:#8126C0">Purple</a></li>
                    <li><a href="#" onclick="handleFillColor('#A0A0A0')" style="background-color:#A0A0A0">Lt. Gray</a></li>
                    <li><a href="#" onclick="handleFillColor('#81C57A')" style="background-color:#81C57A">Lt. Green</a></li>
                    <li><a href="#" onclick="handleFillColor('#9DAFFF')" style="background-color:#9DAFFF">Lt. Blue</a></li>
                    <li><a href="#" onclick="handleFillColor('#29D0D0')" style="background-color:#29D0D0">Cyan</a></li>
                    <li><a href="#" onclick="handleFillColor('#FF9233')" style="background-color:#FF9233">Orange</a></li>
                    <li><a href="#" onclick="handleFillColor('#FFEE33')" style="background-color:#FFEE33">Yellow</a></li>
                    <li><a href="#" onclick="handleFillColor('#E9DEBB')" style="background-color:#E9DEBB">Tan</a></li>
                    <li><a href="#" onclick="handleFillColor('#FFCDF3')" style="background-color:#FFCDF3">Pink</a></li>
                    <li><a href="#" onclick="handleFillColor('#FFFFFF')" style="background-color:#FFFFFF">White</a></li>
                </ul>
            </div>

            <div class="btn-group">
                <button class="btn">Stroke</button>
                <button class="btn" id="menuStrokeColor"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></button>
                <button class="btn dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#" onclick="handleStrokeColor('#000000')" style="background-color:#000000">Black</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#575757')" style="background-color:#575757">Dk. Gray</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#AD2323')" style="background-color:#AD2323">Red</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#2A4BD7')" style="background-color:#2A4BD7">Blue</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#1D6914')" style="background-color:#1D6914">Green</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#814A19')" style="background-color:#814A19">Brown</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#8126C0')" style="background-color:#8126C0">Purple</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#A0A0A0')" style="background-color:#A0A0A0">Lt. Gray</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#81C57A')" style="background-color:#81C57A">Lt. Green</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#9DAFFF')" style="background-color:#9DAFFF">Lt. Blue</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#29D0D0')" style="background-color:#29D0D0">Cyan</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#FF9233')" style="background-color:#FF9233">Orange</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#FFEE33')" style="background-color:#FFEE33">Yellow</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#E9DEBB')" style="background-color:#E9DEBB">Tan</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#FFCDF3')" style="background-color:#FFCDF3">Pink</a></li>
                    <li><a href="#" onclick="handleStrokeColor('#FFFFFF')" style="background-color:#FFFFFF">White</a></li>
                </ul>
            </div>

            <div class="btn-group">
                <button class="btn">Width</button>
                <button class="btn" id="menuStrokeThickness"><span>1</span></button>
                <button class="btn dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#" onclick="handleStrokeWidth(1)">1</a></li>
                    <li><a href="#" onclick="handleStrokeWidth(2)">2</a></li>
                    <li><a href="#" onclick="handleStrokeWidth(3)">3</a></li>
                    <li><a href="#" onclick="handleStrokeWidth(4)">4</a></li>
                    <li><a href="#" onclick="handleStrokeWidth(5)">5</a></li>
                    <li><a href="#" onclick="handleStrokeWidth(6)">6</a></li>
                    <li><a href="#" onclick="handleStrokeWidth(7)">7</a></li>
                    <li><a href="#" onclick="handleStrokeWidth(8)">8</a></li>
                    <li><a href="#" onclick="handleStrokeWidth(9)">9</a></li>
                    <li><a href="#" onclick="handleStrokeWidth(10)">10</a></li>
                </ul>
            </div>
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

        var style = {
            strokeColor : "#FFFFFF",
            strokeWidth : 3,
            fillColor : "#1D6914"
        };

        function applyStyle() {
            scribbol.draw.setObjectStyleSelected(style);

        }

        function handleSelectTool() {
            scribbol.setSelectMode();
            $("#menuAction span").text("Select");
        }

        function handleFreehandTool() {
            scribbol.setFreehandMode();
            $("#menuAction span").text("Freehand");
        }

        function handleFillColor(color) {
            $("#menuFillColor span").css('background-color', color);
            style.fillColor = color;
            applyStyle();
        }

        function handleStrokeColor(color) {
            $("#menuStrokeColor span").css('background-color', color);
            style.strokeColor = color;
            applyStyle();
        }

        function handleStrokeWidth(width) {
            $("#menuStrokeThickness span").text(width);
            style.strokeWidth = width;
            applyStyle();
        }

        function handleCreateText() {
            var text =$("#inputText").val();
            scribbol.draw.drawText(text);
            handleSelectTool();
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

        // Set to freehand tool initially
        handleFreehandTool();

    </script>

</body>
</html>
