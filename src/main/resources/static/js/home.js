 document.getElementById("product_form").addEventListener('submit', async (e) => {
    let productForm = document.getElementById("product_form");
    e.preventDefault();   //prevent submit
    response = await validateForm(productForm);   //if false - validation failed
    if (response) productForm.submit();    //force submit

    /*It's impossible to prevent submit after async function, so I'm doing it at the beginning 
    and then forcing it if validation passed*/
});

document.getElementById("button-cancel-order").onclick = function () {
    location.href = "/home";
};

document.getElementById("button-order-list").onclick = function () {
    location.href = "/orders";
};

async function validateForm(productForm) {

    if (!validateForEmptyFields(productForm)){
        alert("Please, submit required data");
        return false;
    };

    if (!validateDataTypes(productForm) || !validateDataInput(productForm)){     //true - passes validation => if (!true or !true) => won't go into if;
        alert("Please, provide the data of indicated type");
        return false;
    };

    return true;
}

function validateForEmptyFields(productForm){
    if ( (productForm["tableNum"].value != "") && (productForm["workerName"].value != "") && (productForm["explanation"].value != "")) {
        switch(productForm["productType"].value){
            case "headphones":
                if (productForm["headphonesType"].value != "") return true;
            case "headset":
                if (productForm["headsetType"].value != "") return true;
            case "microphone":
                if (productForm["microphoneType"].value != "") return true;
            case "monitor":
                if (productForm["monitorSize"].value != "") return true;
            case "keyboard":
                if ((productForm["keyboardHeight"].value != "") && (productForm["keyboardLength"].value != "") && (productForm["keyboardWidth"].value != "")) return true;
            case "mouse":
                if (productForm["mouseWeight"].value != "") return true;
        }
    };
    return false;
}

function validateDataTypes(productForm){

    numTableNum = Number(productForm["tableNum"].value)-1;
    if (isNaN(numTableNum)) return false;     //fast check - have user submitted number in field Worker table number

    prodType = productForm["productType"].value;
    strWorkerName = Number(productForm["workerName"].value);     // Number(String) = NaN
    strExplanation = Number(productForm["explanation"].value);
    switch(prodType){
        case "monitor":
            numSize = productForm["monitorSize"].value-1;    // String - Number = Number    only if String contains only numbers, else NaN
        case "keyboard":
            numHeight = productForm["keyboardHeight"].value-1;
            numLength = productForm["keyboardLength"].value-1;
            numWidth = productForm["keyboardWidth"].value-1;
        case "mouse":
            numWeight = productForm["mouseWeight"].value-1;
        default:
            break;
    }

    if (isNaN(strWorkerName) && isNaN(strExplanation)){
        switch(prodType){
            case "monitor":
                if (!isNaN(numSize)) return true;
            case "keyboard":
                if (!isNaN(numHeight) && !isNaN(numLength) && !isNaN(numWidth)) return true;
            case "mouse":
                if (!isNaN(numWeight)) return true;
            default:
                return true;
        }
    }
    return false;
}

function validateDataInput(productForm){
    if (productForm["tableNum"].value > 0){
        switch(productForm["productType"].value){
            case "headphones":
                if (productForm["headphonesType"].value === "inEar" || productForm["headphonesType"].value === "withEarPad") return true;
            case "headset":
                if (productForm["headsetType"].value === "builtInMicrophone" || productForm["headsetType"].value === "externalMicrophone") return true;
            case "microphone":
                if (productForm["microphoneType"].value === "desktopMicrophone" || productForm["microphoneType"].value === "lavaliereMicrophone") return true;
            case "monitor":
                if (productForm["monitorSize"].value > 0) return true;
            case "keyboard":
                if ((productForm["keyboardHeight"].value > 0) && (productForm["keyboardLength"].value > 0) && ((productForm["keyboardWidth"].value) > 0)) return true;
            case "mouse":
                if (productForm["mouseWeight"].value > 0) return true;
        };
    };
    return false;
}

var dropdownList = document.getElementById("productType");
dropdownList.onchange = function() {
    var selectedValue = dropdownList.value;
    switch (selectedValue){
    case ("headphones"):
        setBlockDisplay("block", "none", "none", "none", "none", "none");
        break;
    case("headset"):
        setBlockDisplay("none", "block", "none", "none", "none", "none");
        break;
    case("microphone"):
        setBlockDisplay("none", "none", "block", "none", "none", "none");
        break;
    case ("keyboard"):
        setBlockDisplay("none", "none", "none","block", "none", "none");
        break;
    case("monitor"):
        setBlockDisplay("none", "none", "none", "none", "block", "none");
        break;
    case("mouse"):
        setBlockDisplay("none", "none", "none", "none", "none", "block");
        break;
    default:
        setBlockDisplay("none", "none", "none", "none", "none", "none");
    }
}

//if it's not helpdesk user, then we don't need "Order list" button, because anyway user don't have access
window.onload = function checkRole(){
    buttonOrderList = document.getElementById("button-order-list")
    if (document.getElementById("role").innerText.includes("helpdesk")){
        buttonOrderList.style.display = "block";
    }
}

var headphoneBlock = document.getElementById("headphones-block");
var headsetBlock = document.getElementById("headset-block");
var microphoneBlock = document.getElementById("microphone-block");
var keyboardBlock = document.getElementById("keyboard-block");
var monitorBlock = document.getElementById("monitor-block");
var mouseBlock = document.getElementById("mouse-block");
function setBlockDisplay(headphoneDisplay, headsetDisplay, microphoneDisplay, keyboardDisplay, monitorDisplay, mouseDisplay){
    headphoneBlock.style.display = headphoneDisplay;
    headsetBlock.style.display = headsetDisplay;
    microphoneBlock.style.display = microphoneDisplay;
    keyboardBlock.style.display = keyboardDisplay;
    monitorBlock.style.display = monitorDisplay;
    mouseBlock.style.display = mouseDisplay;
}