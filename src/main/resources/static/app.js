let ws = null;
let url = "ws://localhost:8080/color";

let rSlider = null;
let gSlider = null;
let bSlider = null;

let Yy = null;

function connect() {
    ws = new WebSocket(url);
    ws.onopen = function() {
        console.log('Info: Connection Established.');
    };

    ws.onmessage = function(event) {
        console.log(event.data);
        setColor(JSON.parse(event.data));
    };

    ws.onclose = function(event) {
        console.log('Info: Closing Connection.');
    };
}

function disconnect() {
    if (ws != null) {
        ws.close();
        ws = null;
    }
}

function sendColor() {
    if (ws != null) {
         let r = rSlider.get('value'),
             g = gSlider.get('value'),
             b = bSlider.get('value');

        let color = {
                    'red': rSlider.get('value'),
                    'green': gSlider.get('value'),
                    'blue': bSlider.get('value'),
                    'timestamp' : Date.now()
                };

        console.log('Sent to server :: ' + JSON.stringify(color));
        ws.send(JSON.stringify(color));
    } else {
        alert('connection not established.');
    }
}

function setColor(color) {
     colorElement = Yy.one('.color');
     rgbStr = Yy.Color.fromArray([color.red, color.green, color.blue], Yy.Color.TYPES.RGB);
     colorElement.setStyle('backgroundColor', rgbStr);
}

$(function () {
    connect();
    YUI().use('slider', 'color', function(Y){
        // sliders
        Yy = Y;
        rSlider = new Y.Slider({ min: 0, max: 255, value: 0 });
        gSlider = new Y.Slider({ min: 0, max: 255, value: 0 });
        bSlider = new Y.Slider({ min: 0, max: 255, value: 0 });

        // color chip
//      color = Y.one('.color');

        // render sliders
        rSlider.render('#r-slider');
        gSlider.render('#g-slider');
        bSlider.render('#b-slider');

        // register update events
        rSlider.after('thumbMove', function(e) {
            sendColor()
        });
        gSlider.after('thumbMove', function(e) {
            sendColor()
        });
        bSlider.after('thumbMove', function(e) {
            sendColor()
        });

        sendColor();
     });
});

