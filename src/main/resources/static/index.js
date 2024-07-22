class MyRenderer {
    static canvas = null;
    static context = null;
    static frame = 0;
    static lastTimeStamp = performance.now();
    static startTime = performance.now();
    static fpsElement = document.getElementById("fps");
    static response = null;
    static xSize = 0;
    static ySize = 0;
    static arrayBuffer = null;
    static finishedRender = false;
    static instance = null;
    constructor() {
        if(MyRenderer.instance == null) {
            MyRenderer.canvas = document.getElementById("canvas");
            MyRenderer.context = MyRenderer.canvas.getContext("2d");
            MyRenderer.instance = this;
        }
        return MyRenderer.instance;
    }
    static async render(timestamp) {
        if(timestamp != MyRenderer.lastTimeStamp) {
            MyRenderer.lastTimeStamp = timestamp;
            if(MyRenderer.canvas.width != MyRenderer.xSize) MyRenderer.canvas.width = MyRenderer.xSize;
            if(MyRenderer.canvas.height != MyRenderer.ySize) MyRenderer.canvas.height = MyRenderer.ySize;
            MyRenderer.context.rect(0, 0, MyRenderer.xSize, MyRenderer.ySize);
            MyRenderer.context.fillStyle = "red";//for debug
            MyRenderer.context.fill();
            MyRenderer.context.putImageData(new ImageData(new Uint8ClampedArray(MyRenderer.arrayBuffer), MyRenderer.xSize, MyRenderer.ySize), 0, 0);
            MyRenderer.frame++;
            if(MyRenderer.frame == 60) {
                let fps = 60000.0 / (timestamp - MyRenderer.startTime);
                MyRenderer.fpsElement.innerHTML = `${fps.toFixed(2)} fps`;
                MyRenderer.startTime = timestamp;
                MyRenderer.frame = 0;
            }
            MyRenderer.finishedRender = true;
        }
        else requestAnimationFrame(MyRenderer.render);
    }
    static async startLoop() {
        while(true) {
            MyRenderer.response = await fetch("/api/v1");
            MyRenderer.xSize = parseInt(MyRenderer.response.headers.get("X-BB-xSize"));
            MyRenderer.ySize = parseInt(MyRenderer.response.headers.get("X-BB-ySize"));
            MyRenderer.arrayBuffer = await MyRenderer.response.arrayBuffer();
            MyRenderer.finishedRender = false;
            requestAnimationFrame(MyRenderer.render);
            while (!MyRenderer.finishedRender) {
                await new Promise(handler => setTimeout(handler, 1));
            }
        }
    }

}

function setup() {
    const renderer = new MyRenderer();
    MyRenderer.startLoop();
}

setup();
