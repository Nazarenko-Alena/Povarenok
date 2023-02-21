//const { JSDOM } = require( "jsdom" );
//const { window } = new JSDOM( "" );
//const $ = require( "jquery" )( window );

require("@babel/polyfill");
require('/html/index.html');
require('/html/searchRes.html');
require('/html/recipe.html');
require('/html/addRecipe.html');
require('/html/signIn.html');
require('/html/signUp.html');
require('/html/user.html');
require('/css/app.css');

import * as functions from "./function";


const indexHTML = /index.html/;
if(indexHTML.test(document.location.href)){
    document.onreadystatechange = function() {
        if (document.readyState === 'complete') {
            functions.loadInfoIndexRec();
        }
    }
}

if(document.getElementById("findButton")){
    document.getElementById("findButton").addEventListener("click", functions.searchKeywords);
}

if(document.getElementById("applyButton")){
    document.getElementById("applyButton").addEventListener("click", functions.searchFilters);
}


const searchResHTML = /searchRes.html/;
if(searchResHTML.test(document.location.href)){
    document.onreadystatechange = function() {
        if (document.readyState === 'complete') {
            functions.loadInfoSearchRec();
        }
    }
}

const addRecipeHTML = /addRecipe.html/;
if(addRecipeHTML.test(document.location.href)){
    document.onreadystatechange = function() {
        if (document.readyState === 'complete') {
            functions.dragAndDropFunc();
        }
    }
}

if(document.getElementById("inputNameRec")){
    document.getElementById("inputNameRec").addEventListener("change", functions.toggleButtonAddRec);
}

if(document.getElementById("inputDecRec")){
    document.getElementById("inputDecRec").addEventListener("change", functions.toggleButtonAddRec);
}

if(document.getElementById("inputIngRec1")){
    document.getElementById("inputIngRec1").addEventListener("change", functions.toggleButtonAddRec);
}

if(document.getElementById("inputGrRec1")){
    document.getElementById("inputGrRec1").addEventListener("change", functions.toggleButtonAddRec);
}

if(document.getElementById("addIngButton")){
    document.getElementById("addIngButton").addEventListener("click", functions.addIngBlock);
}

if(document.getElementById("inputStepRec")){
    document.getElementById("inputStepRec").addEventListener("change", functions.toggleButtonAddRec);
}

if(document.getElementById("categoryAddRec")){
    document.getElementById("categoryAddRec").addEventListener("change", functions.toggleButtonAddRec);
}

if(document.getElementById("typeCuisAddRec")){
    document.getElementById("typeCuisAddRec").addEventListener("change", functions.toggleButtonAddRec);
}

if(document.getElementById("inputTimeCook")){
    document.getElementById("inputTimeCook").addEventListener("change", functions.toggleButtonAddRec);
}

if(document.getElementById("createRecButton")){
    document.getElementById("createRecButton").addEventListener("click", functions.postAddReq);
}

const recipeHTML = /recipe.html/;
if(recipeHTML.test(document.location.href)){
    document.onreadystatechange = function() {
        if (document.readyState === 'complete') {
            functions.changeHeightRec();
        }
    }
}

if(document.getElementById("addFavorites")){
    document.getElementById("addFavorites").addEventListener("click", functions.postSaveReq);
}

//SIGN_IN
if(document.getElementById("signInForm")){
    document.getElementById("signInForm").addEventListener("submit", functions.isCorrectSignIn);
}

if(document.getElementById("inputLogin")){
    document.getElementById("inputLogin").addEventListener("keydown", functions.toggleButtonSignIn);
}

if(document.getElementById("inputPassword")){
    document.getElementById("inputPassword").addEventListener("keydown", functions.toggleButtonSignIn);
}

//SIGN_UP
if(document.getElementById("signUpForm")){
    document.getElementById("signUpForm").addEventListener("submit", functions.isCorrectSignUp);
}

if(document.getElementById("signUpLogin")){
    document.getElementById("signUpLogin").addEventListener("keydown", functions.toggleButtonSignUp);
}

if(document.getElementById("signUpPassword")){
    document.getElementById("signUpPassword").addEventListener("keydown", functions.toggleButtonSignUp);
}

if(document.getElementById("signUpEmail")){
    document.getElementById("signUpEmail").addEventListener("keydown", functions.toggleButtonSignUp);
}

if(document.getElementById("registerButton")){
    document.getElementById("registerButton").addEventListener("click", functions.postSignUpReq);
}

const userHTML = /user.html/;
if(userHTML.test(document.location.href)){
    document.onreadystatechange = function() {
        if (document.readyState === 'complete') {
            functions.loadInfoUserRec();
        }
    }
}

