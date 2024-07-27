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
    static instance = null;
    static lifetime = 0;

    constructor() {
        if(MyRenderer.instance == null) {
            MyRenderer.canvas = document.getElementById("canvas");
            MyRenderer.context = MyRenderer.canvas.getContext("2d");
            MyRenderer.instance = this;
        }
        return MyRenderer.instance;
    }
    static async refreshData() {
        MyRenderer.response = await fetch("/api/v1");
        MyRenderer.xSize = parseInt(MyRenderer.response.headers.get("X-BB-xSize"));
        MyRenderer.ySize = parseInt(MyRenderer.response.headers.get("X-BB-ySize"));
        MyRenderer.arrayBuffer = await MyRenderer.response.arrayBuffer();
    }
    static async startApp() {
        MyRenderer.response = await fetch("/api/v1/start");
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
                MyRenderer.fpsElement.innerHTML = `${fps.toFixed(2)} fps (VSYNC on)`;
                MyRenderer.startTime = timestamp;
                MyRenderer.frame = 0;
                MyRenderer.lifetime++;
                if(MyRenderer.lifetime >= fps * 5 / 60) {
                    await fetch("api/v1/start");
                    MyRenderer.lifetime = 0;
                }
            }
        }
        else requestAnimationFrame(MyRenderer.render);
    }
    static async startLoop() {
        for(;;) {
            await MyRenderer.refreshData();
            MyRenderer.render(MyRenderer.lastTimeStamp);
        }
    }
}

async function setup() {
    const renderer = new MyRenderer();
    await MyRenderer.startApp();
    await MyRenderer.startLoop();
}

await setup();
