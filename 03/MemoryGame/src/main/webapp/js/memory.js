const delay = ms => new Promise(res => setTimeout(res, ms));

let card1ID = 0;
let card2ID = 0;
let card1Value = 0;
let card2Value = 0;

let firstTry = true;

let points = 0;
let attempts = 4;

function onClick(id) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/MemoryServlet", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.responseType = "json";
    xhttp.onreadystatechange = function () {
        var done = 4, ok = 200;
        if (this.readyState === done && this.status === ok) {
            var cardJSON = this.response;
            updateCard(id, cardJSON.value);
        }
    };
    xhttp.send("id=" + id);
}

function updateCard(id, value) {
    if(firstTry) {
        // update local variables
        card1ID = id;
        card1Value = value;
        // remove onclick event from card1
        document.getElementById(card1ID).removeAttribute('onclick');
        // change card image
        filePath = "images/number-" + card1Value + ".jpg";
        document.getElementById(card1ID).src = filePath;
        // update local controllers
        firstTry = false;
    } else {
        // update local variables
        card2ID = id;
        card2Value = value;
        // remove onclick event from card1
        document.getElementById(card2ID).removeAttribute('onclick');
        // change card image
        filePath = "images/number-" + card2Value + ".jpg";
        document.getElementById(id).src = filePath;
        // update game status
        updateGameStatus();
    }
}

const updateGameStatus = async() => {
    var status;
    // update local punti
    if(card1Value === card2Value) {
        points = points + (2 * card1Value);
        status = true;
    } else {
        points = points - 1;
        status = false;
    }
    // update HTML punti
    document.getElementById("punti").innerHTML = points;
    // update local tentativi
    attempts = attempts - 1;
    console.log("Attemps: " + attempts)
    // update HTML tentativi
    document.getElementById("attempts").innerHTML = attempts;
    // setup nuovo tentativo
    if(attempts === 0) {
        endGame();
    } else {
        firstTry = true;
        if(!status) {
            await delay(1000);
            // reset card back
            document.getElementById(card1ID).src = "images/cardBack.jpg";
            document.getElementById(card2ID).src = "images/cardBack.jpg";
            // reset onclick attribute
            document.getElementById(card1ID).setAttribute('onclick','onClick(this.id)');
            document.getElementById(card2ID).setAttribute('onclick','onClick(this.id)');
        }
    }
}

const endGame = async() => {
    // set game over label
    document.getElementById("gameStatus").innerHTML = "Game Over";
    var images = document.getElementsByClassName("memory-image");
    for(let i = 0; i < images.length; i++) {
        images[i].removeAttribute('onclick');
    }
    // after 1 second redirect to initial page
    await delay(1000);
    console.log("Redirecting to IndexServlet");
    // AJAX redirect to IndexServlet (POST)
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/IndexServlet", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onreadystatechange = function () {
        var done = 4, ok = 200;
        if (this.readyState === done && this.status === ok) {
            window.location = '/IndexServlet';
        }
    }
    xhttp.send("score=" + points);
}