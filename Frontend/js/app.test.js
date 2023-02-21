//const { JSDOM } = require( "jsdom" );
//var { window } = new JSDOM( "" );
//var $ = require( "jquery" )( window );
//assert.equal($.ajax.getCall(0).args[0]['url'], '/todo/items');
//assert($.ajax.calledWithMatch({ url: '/todo/42/items' }));


let sinon = require('sinon');
//const referee = require("@sinonjs/referee");
//const assert = referee.assert;

const functions = require('./function');
var $ = require('./function').$;

describe("Ajax request validation", () => {
    var fakeData = [];

    afterEach(function () {
        $.ajax.restore();
    });

    test("should make an Ajax request \"categories\" in getCategInIndex to the correct URL", function () {
        sinon.stub($, "ajax").yieldsTo("success", fakeData);
        functions.getCategInIndex();
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/categories");
    });

    test("should make an Ajax request \" recipes/last?count=4 \" to the correct URL", function () {
        sinon.stub($, "ajax").yieldsTo("success", fakeData);
        functions.getRecentRec(4);
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/recipes/last?count=4");
    });

    test("should make an Ajax request \" cuisines \" in getCuisinesIndex to the correct URL", function () {
        sinon.stub($, "ajax").yieldsTo("success", fakeData);
        functions.getCuisinesIndex();
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/cuisines");
    });

    test("should make an Ajax request \" users/alena \" in getSavedRecByUserName to the correct URL", function () {
        /*fakeData =[{id: 3, login: 'test23', password: '123456789', email: 'nik@mail.com', addedRecipes:[{id: 2, userLogin: 'alena', name: 'Сырники', imageUrl: '1671528454000.png', dateAdded: '2022-12-20',
                description:"Сырники домашние", category:"Выпечка и десерты", cookingTime:45, cuisine:"Грузинская",
                ingredients:[{id: 3, idRecipe: 2, name: 'Творог', grams: 50}, {id: 4, idRecipe: 2, name: 'Сахар', grams: 25}]}],
            savedRecipes:[{id: 2, userLogin: 'alena', name: 'Сырники', imageUrl: '1671528454000.png', dateAdded: '2022-12-20',
                description:"Сырники домашние", category:"Выпечка и десерты", cookingTime:45, cuisine:"Грузинская",
                ingredients:[{id: 3, idRecipe: 2, name: 'Творог', grams: 50}, {id: 4, idRecipe: 2, name: 'Сахар', grams: 25}]}]}];
       */

        sinon.stub($, "ajax").yieldsTo("success", fakeData);
        functions.getSavedRecByUserName('alena');
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/users/alena");
    });

    test("should make an Ajax request \" recipes/Soup \" to the correct URL", function () {
        /*fakeData = [{id: 2, userLogin: 'alena', name: 'Сырники', imageUrl: '1671528454000.png', dateAdded: '2022-12-20',
            description:"Сырники домашние", category:"Выпечка и десерты", cookingTime:45, cuisine:"Грузинская",
            ingredients:[{id: 3, idRecipe: 2, name: 'Творог', grams: 50}, {id: 4, idRecipe: 2, name: 'Сахар', grams: 25}],
            recipe: "Приготовьте тесто"}];*/
        sinon.stub($, "ajax").yieldsTo("success", fakeData);
        functions.getRecipeByName('Soup');
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/recipes/Soup");
    });

    test("should make an Ajax request \" users/alena \" in getUserSavedAndAddedRecipe to the correct URL", function () {
        sinon.stub($, "ajax").yieldsTo("success", fakeData);
        functions.getUserSavedAndAddedRecipe('alena');
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/users/alena");
    });

    test("should make an Ajax request \" recipes/new \" to the correct URL", function () {
        sinon.stub($, "ajax").yieldsTo("success");
        functions.setNewReq(fakeData);
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/recipes/new");
    });

    test("should make an Ajax request \" registration \" to the correct URL", function () {
        sinon.stub($, "ajax").yieldsTo("success");
        functions.setNewUser(fakeData);
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/registration");
    });

    test("should make an Ajax request \"categories\" in getCategAddRecHtml to the correct URL", function () {
        sinon.stub($, "ajax").yieldsTo("success", fakeData);
        functions.getCategAddRecHtml();
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/categories");
    });

    test("should make an Ajax request \" cuisines \" in getCuisinesAddRecHtml to the correct URL", function () {
        sinon.stub($, "ajax").yieldsTo("success", fakeData);
        functions.getCuisinesAddRecHtml();
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/cuisines");
    });

    test("should make an Ajax request \" recipes/keywords/Суп \" to the correct URL", function () {
        sinon.stub($, "ajax").yieldsTo("success", fakeData);
        functions.getSearchResByKeyword("Суп");
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/recipes/keywords/Суп");
    });

    test("should make an Ajax request \" recipes/Выпечка/Американская \" to the correct URL", function () {
        sinon.stub($, "ajax").yieldsTo("success", fakeData);
        functions.getSearchResByFilter("Выпечка", "Американская");
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/recipes/Выпечка/Американская");
    });

    test("should make an Ajax request \" recipes//Американская \" to the correct URL", function () {
        sinon.stub($, "ajax").yieldsTo("success", fakeData);
        functions.getSearchResByFilter("", "Американская");
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/recipes//Американская");
    });

    test("should make an Ajax request \" recipes/Выпечка/ \" to the correct URL", function () {
        sinon.stub($, "ajax").yieldsTo("success", fakeData);
        functions.getSearchResByFilter("Выпечка", "");
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/recipes/Выпечка/");
    });

    test("should make an Ajax request \" recipes/alena/save/Борщ \" to the correct URL", function () {
        sinon.stub($, "ajax").yieldsTo("success");
        functions.addRecInFav("alena", "Борщ");
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/recipes/alena/save/Борщ");
    });

    test("should make an Ajax request \" recipes/alena/delete/Борщ \" to the correct URL", function () {
        sinon.stub($, "ajax").yieldsTo("success");
        functions.deleteRecFromFav("alena", "Борщ");
        expect($.ajax.getCall(0).args[0]['url']).toBe("http://localhost:8080/povarenok/recipes/alena/delete/Борщ");
    });



    /* test("assert that <title> is correct", async () => {
             const title = await page.title();
             expect(title).toBe("Povarenok");
     });*/


});

describe("Validation of element values", () => {

    test("validRecValue correct", function () {
        expect(functions.validRecValue("alena", "Soup", "aa", "1",
            "Abnzmzmzmzmzmdhfjkshaydjf", "2", 1, 3, "s"))
            .toBe(true);
    });

    test("validRecValue nameRec incorrect", function () {
        expect(functions.validRecValue("a", "Soup", "aa", "1",
            "Abnzmzmzmzmzmdhfjkshaydjf", "2", 1, 3, "s"))
            .toBe(false);
    });

    test("validRecValue DecRec incorrect", function () {
        expect(functions.validRecValue("alena", "S", "aa", "1",
            "Abnzmzmzmzmzmdhfjkshaydjf", "2", 1, 3, "s"))
            .toBe(false);
    });

    test("validRecValue inputIngRecOne incorrect", function () {
        expect(functions.validRecValue("alena", "Soup", "a", "1",
            "Abnzmzmzmzmzmdhfjkshaydjf", "2", 1, 3, "s"))
            .toBe(false);
    });

    test("validRecValue inputGrRecOne incorrect", function () {
        expect(functions.validRecValue("alena", "Soup", "aa", "",
            "Abnzmzmzmzmzmdhfjkshaydjf", "2", 1, 3, "s"))
            .toBe(false);
    });

    test("validRecValue inputStepRec incorrect", function () {
        expect(functions.validRecValue("alena", "Soup", "aa", "1",
            "Abnzmzmzmzmzmd", "2", 1, 3, "s"))
            .toBe(false);
    });

    test("validRecValue inputTimeCook incorrect", function () {
        expect(functions.validRecValue("alena", "Soup", "aa", "1",
            "Abnzmzmzmzmzmdhfjkshaydjf", "", 1, 3, "s"))
            .toBe(false);
    });

    test("validRecValue typeCuisAddRec incorrect", function () {
        expect(functions.validRecValue("alena", "Soup", "aa", "1",
            "Abnzmzmzmzmzmdhfjkshaydjf", "2", 0, 3, "s"))
            .toBe(false);
    });

    test("validRecValue categoryAddRec incorrect", function () {
        expect(functions.validRecValue("alena", "Soup", "aa", "1",
            "Abnzmzmzmzmzmdhfjkshaydjf", "2", 1, 0, "s"))
            .toBe(false);
    });

    test("validRecValue imageTmp incorrect", function () {
        expect(functions.validRecValue("alena", "Soup", "aa", "1",
            "Abnzmzmzmzmzmdhfjkshaydjf", "2", 1, 3, ""))
            .toBe(false);
    });


    test("validSignInValue correct", function () {
        expect(functions.validSignInValue("alena", "12345678")).toBe(true);
    });

    test("validSignInValue username incorrect", function () {
        expect(functions.validSignInValue("a", "12345678")).toBe(false);
    });

    test("validSignInValue password incorrect", function () {
        expect(functions.validSignInValue("alena", "12345")).toBe(false);
    });


    test("validSignUpValue correct", function () {
        expect(functions.validSignUpValue("alena", "12345678", "al")).toBe(true);
    });

    test("validSignUpValue username incorrect", function () {
        expect(functions.validSignUpValue("a", "12345678", "al")).toBe(false);
    });

    test("validSignUpValue password incorrect", function () {
        expect(functions.validSignUpValue("alena", "12345", "al")).toBe(false);
    });

    test("validSignUpValue email incorrect", function () {
        expect(functions.validSignUpValue("alena", "123456789", "")).toBe(false);
    });


})

describe("CurrTopOff calc validation", () => {
    test("setCurrTopOff correct", function () {
        expect(functions.setCurrTopOff(20)).toBe(20);
    });

    test("increaseCurrTopOff correct", function () {
        let currTopOff = functions.setCurrTopOff(20);
        currTopOff = functions.increasePerem(currTopOff, 6);
        expect(currTopOff).toBe(26);
    });

    test("calcHeightBlock correct", function () {
        expect(functions.calcHeightBlock(0)).toBe(70);
    });
})

describe("CurrLeftOff calc validation", () => {
    test("setCurrLeftOff correct", function () {
        expect(functions.setCurrLeftOff(20)).toBe(20);
    });

    test("increaseCurrLeftOff correct", function () {
        let currLeftOff = functions.setCurrLeftOff(20);
        currLeftOff = functions.increasePerem(currLeftOff, 6);
        expect(currLeftOff).toBe(26);
    });

})

describe("CurrTopIng calc validation", () => {
    test("setCurrTopIng correct", function () {
        expect(functions.setCurrTopIng(20)).toBe(20);
    });

    test("increaseCurrTopIng correct", function () {
        let currTopIng = functions.setCurrTopIng(20);
        currTopIng = functions.increasePerem(currTopIng, 6);
        expect(currTopIng).toBe(26);
    });

})





// describe("SignIn", () => {
//     beforeAll(async () => {
//         browser = await puppeteer.launch();
//         page = await browser.newPage();
//         await page.goto('http://localhost:63342/Frontend/html/signIn.html?_ijt=5o024mqm82c7heta8kov0f1jfr&_ij_reload=RELOAD_ON_SAVE');
//     });
//
//     afterAll(() => {
//         page.close();
//         browser.close();
//     });
//
//     test("The \"Войти\" button should be inactive if both fields are empty", async () => {
//         await page.click("input[name=inputLogin]");
//         await page.type("input[name=inputLogin]", "");
//
//         await page.click("input[name=inputPassword]");
//         await page.type("input[name=inputPassword]", "");
//
//         const disabledButton = await page.$eval("button[name=enterButton]", el => el.getAttribute("disable"));
//         expect(disabledButton).toBeTruthy();
//     });
//
//     test("assert that <title> is correct", async () => {
//         const title = await page.title();
//         expect(title).toBe("Povarenok");
//     });
//
//
//
// });