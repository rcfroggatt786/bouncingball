setup();

function setup() {
    const canvas = document.getElementById("canvas");
    const context = canvas.getContext("2d");
    main_loop(canvas, context);
}

async function main_loop(canvas, context) {
    let frame = 0;
    let startTime = performance.now();
    const fpsElement = document.getElementById("fps");
    while(true) {
        let response = await fetch("/api/v1");
        if (!response.ok) {
            console.error("Could not fetch image data");
        }
        else {
            let xSize = parseInt(response.headers.get("X-BB-xSize"));
            let ySize = parseInt(response.headers.get("X-BB-ySize"));
            canvas.width = xSize;
            canvas.height = ySize
            response.arrayBuffer().then (
                (arrayBuffer) => {
                    context.putImageData(new ImageData(new Uint8ClampedArray(arrayBuffer), xSize, ySize), 0, 0);
                }
            );
        }
        frame++;
        if(frame == 120) {
            let currTime = performance.now();
            let fps = 120000.0 / (currTime - startTime);
            startTime = currTime;
            frame = 0;
            fpsElement.innerHTML = `${fps.toFixed(2)} fps`;
        }
    }
}
