document.addEventListener('DOMContentLoaded', () => {
    let canvas = document.getElementById('1');
    canvas.with =  document.documentElement.clientWidth;
    canvas.heigth =  document.documentElement.clientHeight;

    canvas.setAttribute('width', document.documentElement.clientWidth);
    canvas.setAttribute('height',  document.documentElement.clientHeight);

	let ctx = canvas.getContext('2d');
    x = 0;
    y = 0; 
    const xStep = 8;
    let yStep = 2;

    moveDown();

    function moveDown() {
        if(130 + x * xStep  < window.innerWidth){
            let refreshIntervalId = setInterval( function(){
                
                canvas.width = canvas.offsetWidth;
                ctx.beginPath();
                ctx.arc(50 + x++ * xStep, 50 + y++ * yStep, 50, 0, 2*Math.PI);
                ctx.fillStyle = 'red';
                ctx.fill();
                yStep += 2;

                if(180 + y  * yStep  <= window.innerHeight){
                    clearInterval(refreshIntervalId);
                    moveDown();
                } else {
                    clearInterval(refreshIntervalId);
                    moveUp();
                }
            }, 1000/30);
        } 
    }

    function moveUp() {
        if(130 + x * xStep < window.innerWidth){
            let refreshIntervalId = setInterval( function(){
               
                canvas.width = canvas.offsetWidth;
                ctx.beginPath();
                ctx.arc(50 + x++ * xStep, 50 + y-- * yStep, 50, 0, 2*Math.PI);
                ctx.fillStyle = 'red';
                ctx.fill();
                yStep -= 2;

                if(50 + y * yStep > 0 && y > 0){
                    clearInterval(refreshIntervalId);
                    moveUp();
                } else {
                    clearInterval(refreshIntervalId);
                    moveDown();
                }
            }, 1000/30);
        } 
    }
    
})