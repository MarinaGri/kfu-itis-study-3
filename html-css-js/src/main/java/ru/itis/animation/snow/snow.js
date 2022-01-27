document.addEventListener('DOMContentLoaded', () => { 
    let wigth = document.documentElement.clientWidth - 300;
    let body = document.getElementsByTagName('body')[0];

    let editHtml1 = function (margin) {
        return `<div class="snow" style="margin-left: ${margin}"><div class="snowflake"></div></div>`
    }

    let editHtml2 = function (margin) {
        return `<div class="snow2" style="margin-left: ${margin}"><div class="snowflake2"></div></div>`
    }

    addSnowflake1 = function(){
        let margin = Math.floor(Math.random() * wigth) + 'px';
        body.innerHTML = editHtml1(margin);
            
    }

    addSnowflake2 = function(){
        let margin = Math.floor(Math.random() * wigth) + 'px';
        body.innerHTML = body.innerHTML + editHtml2(margin);
    }

    addSnowflake3 = function(){
        let margin = Math.floor(Math.random() * wigth) + 'px';
        let margin2 = Math.floor(Math.random() * wigth) + 'px';
        body.innerHTML = body.innerHTML + editHtml1(margin2) + editHtml2(margin);
    }
    
    setInterval(addSnowflake1, 10000);
    setInterval(addSnowflake2, 20000);
    setInterval(addSnowflake3, 30000);
})
