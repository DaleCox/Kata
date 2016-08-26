//run using node Node_script.js  [1,2,3,4,5]
//array is optional

var kata = require("./calculation_script.js");

function runMain(args){
    console.log("Starting Potter Kata");
    console.log("Argument Input ",args[2]);
    var argInput;
    if(args[2])
        argInput =JSON.parse(args[2]);
    else
        argInput = [];
    var totalPrice = kata.main(argInput);

    console.log("total price arg sets, ", totalPrice);

}

runMain(process.argv);