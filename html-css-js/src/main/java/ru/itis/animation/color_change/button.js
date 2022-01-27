document.addEventListener('DOMContentLoaded', function() {
    colors = ["red", "green", "blue"];
    i = 0;
    changeColor = function(i){
    document.getElementsByTagName('body')[0].style.backgroundColor = colors[i];
    }

    document.getElementById('1').addEventListener('click', () => {
        i++;
        changeColor( i %= 3);
    })

    btns = document.getElementsByClassName('btn');

    Array.from(btns).forEach(element => {
        element.addEventListener('click', () => {
            document.getElementsByTagName('body')[0].style.backgroundColor = element.style.backgroundColor;
        })
    });

})
