document.addEventListener('DOMContentLoaded', () => {
    i = 0;
    c = document.getElementById('1');
    const yStep = 2;
    const xStep = 8;

    moveDown();

    function moveDown() {
        if(parseInt(c.style.left) + 100  + xStep < window.innerWidth){
            let refreshIntervalId = setInterval( function(){
                
                c.style.left = parseInt(c.style.left) + xStep +'px';
                i += yStep;
                c.style.top = parseInt(c.style.top) + i +'px';

                if(parseInt(c.style.top) + 100  + i <= window.innerHeight){
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
        if(parseInt(c.style.left) +  100 + xStep < window.innerWidth){
            let refreshIntervalId = setInterval( function(){
               
                c.style.left = parseInt(c.style.left) + xStep +'px';
                i -= yStep;
                c.style.top = parseInt(c.style.top) - i +'px';

                if(parseInt(c.style.top) - i > 0 && i > 0){
                    console.log(c.style.top)
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
