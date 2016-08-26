var assert = require("assert");
var kata = require("./calculation_script.js");

describe('1 TEST single set', function(){
	it('1a1 test singleSet 1', function(){
        var singleSet = [1];
        assert.equal(8,kata.main(singleSet));//expected result 8
	});
    it('1a2 test singleSet 2', function(){
        var singleSet = [1,2];
        assert.equal(15.2,kata.main(singleSet));//expected result 15.2
	});
    it('1a3 test singleSet 3', function(){
        var singleSet = [1,2,3];
        assert.equal(21.6,kata.main(singleSet));//expected result 21.6
	});
    it('1a4 test singleSet 4', function(){
        var singleSet = [1,2,3,4];
        assert.equal(25.6,kata.main(singleSet));//expected result 25.6
	});
	it('1a5 test singleSet all 5', function(){
        var singleSet = [1,2,3,4,5];
        assert.equal(30,kata.main(singleSet));//expected result 30
	});
});

describe('2 TEST multipule sets', function(){
	it('2a1 test two sets presorted', function(){
        var sets = [1,1,2,2,3,3,4,5];
        assert.equal(51.2,kata.main(sets));//expected result 51.2
	});
    it('2a2 test two sets unsorted', function(){
        var sets = [1,2,3,3,4,5,1,2];
        assert.equal(51.2,kata.main(sets));//expected result 51.2
	});
    it('2a3 test 5 uneven sets ', function(){
        var sets = [1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4,5,5,5,5,5];
        assert.equal(145.6,kata.main(sets));//expected result 145.6
	});
});