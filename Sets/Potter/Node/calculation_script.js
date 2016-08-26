
function main(basket){        
    var argInput;
    if(basket)
        argInput =basket;
    else
        argInput = [];
    var argSets = sortSets(argInput); 
    balanceSets(argSets);
    var totalPrice = calculateTotalPrice(argSets);
    
    return totalPrice;
}

//assumes that larger arrays will have smaller index
//need to think about setting this as a recursive call, as it stand now it will only be called once
function balanceSets(arrayOfSets){
    var balanced = false;
    if(arrayOfSets.length < 2)
        return balanced;
    
    for(var i=1; i < arrayOfSets.length; ++i){
        //if the previous array length differs by 2 or more the current try to balance
        if(Math.abs(arrayOfSets[i-1].length - arrayOfSets[i].length) > 1)
        {
            //try to pull last val from last array
            var lastVal = arrayOfSets[i-1].pop();
            //check to see if the current array contains the value
            if(arrayOfSets[i].toString().includes(lastVal)){
                arrayOfSets[i-1].push(lastVal);//could have skipped this if I selected the element by length - 1
            }else{
                arrayOfSets[i].push(lastVal);
                balanced = true;
            }
             
        }
    }

    return balanced

}

function calculateTotalPrice(sets){
    var totalPrice = 0;
    for(var i =0; i < sets.length; i++ ){
        totalPrice += calculateSetPrice(sets[i].length);
    }

    return totalPrice;
}

function calculateSetPrice(setLength){
    switch(setLength){
        case 1:
            return (setLength * 8);
        case 2:
            return (setLength * (8 * .95));
        case 3:
            return (setLength * (8 * .90));
        case 4:
            return (setLength * (8 * .80));
        default:
            return (setLength * (8 * .75));
    }
}

function sortSets(basket){
    //console.log("Basket ",basket);//debug
    if(!basket || basket.length == 0)
        return [[]];

    //sort basket
    basket.sort();//built in sort works for number 1-9 if we were using greater tahn 9 we would need to write a customer compare function. 
    //console.log("Sorted Basket ", basket);//debug

    //build sets
    var sets = [];
    sets.push([basket[0]]);    
    
    for(var i = 1; i < basket.length; ++i){
        //console.log(basket[i])//debug       
        for(var j = 0; j < sets.length; ++j){
            //console.log("Checking inclusion");//debug
            if(!sets[j].toString().includes(basket[i])){
                //console.log("is not there");//debug
                sets[j].push(basket[i])
                break;//shoudl break j
            }else if(j == sets.length -1){
                    //console.log("need new set");//debug
                //need a new set
                sets[j+1] = [basket[i]];
                break;
            }               
        }
        
    }

    //console.log("Sorted Sets ", sets);//debug
    return sets;
}


//main(process.argv);
module.exports = {
    main:main
}