//const $ = require("./jquery-3.6.2");

const { JSDOM } = require( "jsdom" );
const { window } = new JSDOM( "" );
const $ = require( "jquery" )( window );

let indexBlockIng = 1;
let currTopIng = 381;
let imageTmpUrl;

function setUserName() {

    let username = document.cookie.match('(^|;) ?' + "username" + '=([^;]*)(;|$)');

    let divUserName = document.createElement('div');

    if (username) {
        divUserName.innerHTML = '<div>\n' +
            ' <a id="signInButton" href="user.html" target="_self">' + username[2] + '</a>\n' +
            ' </div>';
    } else {
        divUserName.innerHTML = '<div>\n' +
            ' <a id="signInButton" href="signIn.html" target="_self"> Войти </a>\n' +
            ' </div>';
    }

    document.getElementById("headerBlock").append(divUserName);
}

function deleteIngBlock(id) {
    document.getElementById("inputIngRec" + id).remove();
    document.getElementById("inputGrRec" + id).remove();
    document.getElementById("deleteIng" + id).remove();

    document.getElementById('addIngButton').style.top =
        document.getElementById('addIngButton').offsetTop - 50 + 'px';

    document.getElementById('addIngText').style.top =
        document.getElementById('addIngText').offsetTop - 50 + 'px';

    document.getElementById('lineAddIngRec').style.top =
        document.getElementById('lineAddIngRec').offsetTop - 50 + 'px';

    document.getElementById('inputStepRec').style.top =
        document.getElementById('inputStepRec').offsetTop - 50 + 'px';


    for (let i = Number(id) + 1; i <= indexBlockIng; i++) {
        let newIdIng = "inputIngRec" + i;
        let newIdGr = "inputGrRec" + i;
        let newIdImg = "deleteIng" + i;

        if (document.getElementById(newIdIng)) {
            document.getElementById(newIdIng).style.top = document.getElementById(newIdIng).offsetTop - 50 + 'px';
            document.getElementById(newIdGr).style.top = document.getElementById(newIdGr).offsetTop - 50 + 'px';
            document.getElementById(newIdImg).style.top = document.getElementById(newIdImg).offsetTop - 50 + 'px';
        }
    }

    toggleButtonAddRec();
}

function setCurrTopIng(val){
    currTopIng = val;
    return currTopIng;
}

function increasePerem(perem, param){
    perem += param;
    return perem;
}

function addBlock() {
    document.getElementById('createRecButton').disabled = true;
    for (let i = 1; i <= indexBlockIng; i++) {
        let newIdIng = "inputIngRec" + i;

        if (document.getElementById(newIdIng)) {
            setCurrTopIng(document.getElementById(newIdIng).offsetTop);
        }
    }

    setCurrTopIng(increasePerem(currTopIng, 50));
    indexBlockIng++;

    let newIdIng = "inputIngRec" + indexBlockIng;
    let newIdGr = "inputGrRec" + indexBlockIng;
    let newIdImg = "deleteIng" + indexBlockIng;

    let divId = document.createElement('div');
    divId.innerHTML = '<input class="addIngRec" type="text" ' +
        'placeholder="Добавьте название ингредиента" minlength="2" maxlength="100" required\n' +
        '            oninput="this.value = this.value.replace(/[^а-яёЁ\\s]/gi, \'\');"\n' +
        '            id=' + newIdIng + '>';

    let divGr = document.createElement('div');
    divGr.innerHTML = '<div>\n' +
        '        <input class="addGrRec" type="text" placeholder="граммы" minlength="1" maxlength="5" required\n' +
        '               oninput="this.value = this.value.replace(/[^0-9\\s]/gi, \'\');"\n' +
        '               id=' + newIdGr + '>\n' +
        '      </div>';

    let divImg = document.createElement('div');
    divImg.innerHTML = '<div>\n' +
        '        <button type="button" class="deleteIngButton" id=' + newIdImg + '>\n' +
        '          <img src="../img/deleteIng.png" class="deleteIngImg" alt="Удалить ингредиент" ' +
        '>\n' +
        '        </button>\n' +
        '      </div>';

    document.getElementById("addedIngRec").append(divId);
    document.getElementById("addedIngRec").append(divGr);
    document.getElementById("addedIngRec").append(divImg);

    if(document.getElementById(newIdImg)){
        document.getElementById(newIdImg).addEventListener("click", () => deleteIngBlock(indexBlockIng));
    }

    if(document.getElementById(newIdGr)){
        document.getElementById(newIdGr).addEventListener("change", toggleButtonAddRec);
    }

    if(document.getElementById(newIdIng)){
        document.getElementById(newIdIng).addEventListener("change", toggleButtonAddRec);
    }

    document.getElementById(newIdIng).style.top = currTopIng + 'px';
    document.getElementById(newIdGr).style.top = currTopIng + 'px';
    document.getElementById(newIdImg).style.top = currTopIng + 11 + 'px';
}

function addIngBlock() {
    addBlock();

    document.getElementById('addIngButton').style.top =
        document.getElementById('addIngButton').offsetTop + 50 + 'px';

    document.getElementById('addIngText').style.top =
        document.getElementById('addIngText').offsetTop + 50 + 'px';

    document.getElementById('lineAddIngRec').style.top =
        document.getElementById('lineAddIngRec').offsetTop + 50 + 'px';

    document.getElementById('inputStepRec').style.top =
        document.getElementById('inputStepRec').offsetTop + 50 + 'px';
}

function toggleButtonAddRec() {

    document.getElementById('createRecButton').disabled = !validRecValue(
        document.getElementById('inputNameRec').value,
        document.getElementById('inputDecRec').value,
        document.getElementById('inputIngRec1').value,
        document.getElementById('inputGrRec1').value,
        document.getElementById('inputStepRec').value,
        document.getElementById('inputTimeCook').value,
        document.getElementById('typeCuisAddRec').selectedIndex,
        document.getElementById('categoryAddRec').selectedIndex,
        imageTmpUrl );

    for (let i = 1; i <= indexBlockIng; i++) {
        let newIdIng = "inputIngRec" + i;
        let newIdGr = "inputGrRec" + i;

        if (document.getElementById(newIdIng)) {
            if ((document.getElementById(newIdIng).value.length < 2)
                || (document.getElementById(newIdGr).value.length === 0)) {
                document.getElementById('createRecButton').disabled = true;
            }
        }
    }
}

function validRecValue(nameRec, DecRec, inputIngRecOne, inputGrRecOne, inputStepRec,
                       inputTimeCook, typeCuisAddRec, categoryAddRec, imageTmp ){
    return(((nameRec.length > 1) && (DecRec.length > 1)
        && (inputIngRecOne.length > 1) && (inputGrRecOne.length > 0)
        && (inputStepRec.length > 20) && (inputTimeCook.length > 0)
        && !(typeCuisAddRec === 0) && !(categoryAddRec === 0)));
}

function toggleButtonSignIn() {
    document.getElementById('enterButton').disabled = !validSignInValue(
        document.getElementById('inputLogin').value,
        document.getElementById('inputPassword').value);

    document.cookie = "username=" + document.getElementById('inputLogin').value;
    document.cookie = "logged_in=yes";
}

function validSignInValue(username, password){
    return(((username.length > 1) && (password.length > 7)));
}

function searchKeywords() {
    document.cookie = "keyword=" + document.getElementById('searchLine').value;
}

function searchFilters() {
    document.cookie = "filter1=" + document.getElementById('categoryIndex').value;
    document.cookie = "filter2=" + document.getElementById('typeCuis').value;
}

function remEvent(event) {
    event.preventDefault();
    alert("Пароль или Логин введены неверно");
}

function isCorrectSignIn() {
 /*   let username = document.getElementById("inputLogin").value;
    let password = document.getElementById("inputPassword").value;
    document.removeEventListener('submit', remEvent);

    if ((password.length < 8) || (password.length > 12)
        || (username.length < 2) || (username.length > 30)) {
        document.addEventListener('submit', remEvent);
    } /*Добавить проверку, существует ли такой пользователь и корректность данных*/

}

function changeHeightRec() {
    setUserName();

    let top = 145;
    let offset = 21;

    let nameHeight = document.getElementById('infoNameRec').scrollHeight;
    document.getElementById('infoNameRec').style.height = nameHeight + 'px';

    document.getElementById('infoRecLeftBlock').style.top = offset + top + nameHeight + 'px';

    let decHeight = document.getElementById('infoDecRec').scrollHeight + 5;
    document.getElementById('infoDecRec').style.height = decHeight + 'px';

    let ingHeight = document.getElementById('infoIngRec').scrollHeight + 5;
    document.getElementById('infoIngRec').style.height = ingHeight + 'px';
    document.getElementById('infoIngBlock').style.top = offset + decHeight + 'px';

    document.getElementById('infoStepBlock').style.top = offset + offset + 40 + decHeight + ingHeight + 'px';
    document.getElementById('infoStepRec').style.height = document.getElementById('infoStepRec').scrollHeight + 'px';

    document.getElementById('addFavorites').disabled = !(document.getElementById('signInButton').innerText !== "Войти");

    let nameRec = document.cookie.match('(^|;) ?' + "nameRec" + '=([^;]*)(;|$)');
    let username = document.cookie.match('(^|;) ?' + "username" + '=([^;]*)(;|$)');
    let nameR = nameRec[2].replace(/["']/g,'');

    getRecipeByName(nameR);

    if(username) {
        getSavedRecByUserName(username[2]);
    }
}

function getSavedRecByUserName(username){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/povarenok/users/' + username, // адрес запроса
        dataType: 'json', // тип ожидаемых данных,
        contentType: 'application/json',
        success: function (data) {
            if(data.length !== 0) {
                let countFavRec = data["savedRecipes"].length; /*ДОБАВИТЬ ПОЛУЧЕНИЕ ЧИСЛА ИЗБРАННЫХ РЕЦЕПТОВ*/

                for (let i = 0; i < countFavRec; i++) {
                    let nameRec = document.cookie.match('(^|;) ?' + "nameRec" + '=([^;]*)(;|$)');
                    let nameR = nameRec[2].replace(/["']/g, '');
                    if (data["savedRecipes"][i]["name"] === nameR) {
                        document.getElementById('addFavorites').innerText = "Удалить из избранного";

                    }
                }
            }
        }, // обработка ответа от сервера
        error: function (data) {
        },
    });
}

function getRecipeByName(nameR){
    let url_ = "http://localhost:8080/povarenok/recipes/" + nameR;
    let rec = [];

    $.ajax({
        type: 'GET',
        url: url_,
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            if(data.length !== 0) {
                for (const key in data) {
                    rec.push(data[key]);
                }

                document.getElementById('infoNameRec').innerText = rec[2];

                document.getElementById('infoDecRec').innerText = rec[9];

                document.getElementById('infoStepRec').innerText = rec[10];

                document.getElementById('infoCatRec').innerText = rec[6];

                document.getElementById('infoTypeCusRec').innerText = rec[5];

                document.getElementById('infoAuthorRec').innerText = rec[1];

                let strIng = "";

                for (let i = 0; i < rec[8].length; i++) {
                    let dig = i + 1;
                    strIng += dig + ". " + rec[8][i]["name"] + " " + rec[8][i]["grams"] + " грамм \n ";
                }
                document.getElementById('infoIngRec').innerText = strIng;


                document.getElementById('infoTimeCookingRec').innerText = rec[7];

                document.getElementById('infoDateRec').innerText = rec[4];

                let src = "../downloads/" + rec[3];

                document.getElementById('infoRecImage').setAttribute('src', src);
            }
        }, // обработка ответа от сервера
        error: function (data) {
        },
    });
}

function toggleButtonSignUp() {

    document.getElementById('registerButton').disabled = !validSignUpValue(
        document.getElementById('signUpLogin').value,
        document.getElementById('signUpPassword').value,
        document.getElementById('signUpEmail').value
    );

    document.cookie = "username=" + username;
    document.cookie = "logged_in=yes";
}

function validSignUpValue(username, password, email){
    return((username.length > 1) && (password.length > 7) && (email.length > 0));
}

function isCorrectSignUp() {
    /*Добавить проверку, существует ли такой пользователь */
}

let files;
let index;

let addedCatAddRec = false;
let addedCusAddRec = false;

function dragAndDropFunc() {
    const types = ['image/jpeg', 'image/png'];
    imageTmpUrl = "";
    let dragAndDrop = document.querySelector('.dropImgZone');

    dragAndDrop.addEventListener('dragenter', (e) => {
        e.preventDefault();
        dragAndDrop.classList.add('active');
    })
    dragAndDrop.addEventListener('dragleave', (e) => {
        e.preventDefault();
        dragAndDrop.classList.remove('active');
    })
    dragAndDrop.addEventListener('dragover', (e) => {
        e.preventDefault();
    })
    dragAndDrop.addEventListener('drop', (e) => {
        e.preventDefault();
        files = e.dataTransfer.files;
        index = e.dataTransfer.files.length - 1;
        if (types.includes(files[e.dataTransfer.files.length - 1].type)) {
            if (document.getElementById("addImg")) {
                document.getElementById("addImg").remove();
            }
            if (document.getElementById("addImgRecText")) {
                document.getElementById("addImgRecText").remove();
            }

            imageTmpUrl = URL.createObjectURL(files[e.dataTransfer.files.length - 1]);
            document.querySelector('.images').innerHTML = '<img src="${imageTmpUrl}" class="image" alt="">';
            toggleButtonAddRec();
        }

        dragAndDrop.classList.remove('active');
    })

    getCategAddRecHtml();
    getCuisinesAddRecHtml();

    setUserName();
}

function getCuisinesAddRecHtml(){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/povarenok/cuisines', // адрес запроса
        dataType: 'json', // тип ожидаемых данных,
        contentType: 'application/json',
        success: function (data) {
            if (!addedCusAddRec) {
                for (let i = 0; i < data.length; i++) {
                    let divCat = document.createElement('option');
                    divCat.innerHTML = '<option value="' + data[i]["id"] + '">' + data[i]["name"] + '</option>';
                    document.getElementById("typeCuisAddRec").append(divCat);
                    if (i === data.length - 1) {
                        addedCusAddRec = true;
                    }
                }
            }
        }, // обработка ответа от сервера
        error: function (data) {
        },
    });
}

function getCategAddRecHtml(){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/povarenok/categories', // адрес запроса
        dataType: 'json', // тип ожидаемых данных,
        contentType: 'application/json',
        success: function (data) {
            if (!addedCatAddRec) {
                for (let i = 0; i < data.length; i++) {
                    let divCat = document.createElement('option');
                    divCat.innerHTML = '<option value="' + data[i]["id"] + '">' + data[i]["name"] + '</option>';
                    document.getElementById("categoryAddRec").append(divCat);
                    if (i === data.length - 1) {
                        addedCatAddRec = true;
                    }
                }
            }
        }, // обработка ответа от сервера
        error: function (data) {
        },
    });
}

function loadInfoSearchRec() {
    setUserName();
    let keyword = document.cookie.match('(^|;) ?' + "keyword" + '=([^;]*)(;|$)');
    let filter1 = document.cookie.match('(^|;) ?' + "filter1" + '=([^;]*)(;|$)');
    let filter2 = document.cookie.match('(^|;) ?' + "filter2" + '=([^;]*)(;|$)');

    if (keyword != null && keyword[2] !== "") {
        let searchRec = keyword[2];

        getSearchResByKeyword(searchRec);

    } else if (filter1 != null && filter2 != null && filter1[2] !== "" && filter2[2] !== "") {

        getSearchResByFilter(filter1[2], filter2[2]);
    }
}

let currLeftOff;

function setCurrLeftOff(val){
    currLeftOff = val;
    return currLeftOff;
}

function getSearchResByKeyword(searchRec){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/povarenok/recipes/keywords/' + searchRec,
        dataType: 'json', // тип ожидаемых данных,
        contentType: 'application/json',
        success: function (data) {
            if(data.length !== 0) {
                let countSearchBlock = data.length; /*ДОБАВИТЬ ПОЛУЧЕНИЕ ЧИСЛА НАЙДЕННЫХ РЕЦЕПТОВ*/

                //document.getElementById('textCountRes').innerText = "ВСЕГО РЕЦЕПТОВ НАЙДЕНО: " + countSearchBlock;
                document.getElementById('textCountRes').innerText = "ВСЕГО РЕЦЕПТОВ НАЙДЕНО: " + countSearchBlock;
                document.getElementById('textFilterRes').innerText = "Ключевые слова: " + searchRec;

                setCurrLeftOff(73);

                setCurrTopOff(310);

                for (let i = 0; i < data.length; i++) {
                    let newIdRecImg = "recImage" + i;
                    let newIdNameRec = "nameRec" + i;
                    let newIdNameAuthor = "nameAuthor" + i;
                    let newIdTimeCooking = "timeCooking" + i;
                    let newIdBlockSearch = "blockSearch" + i;

                    let divId = document.createElement('div');

                    let nameRec = "\'" + data[i]["name"] + "\'";
                    let src = "../downloads/" + data[i]["imageUrl"];

                    divId.innerHTML = '<div class="blockSearchRec" id=' + newIdBlockSearch + '>\n' +
                        ' <div><img src="' + src + '" class="recImage" id=' + newIdRecImg + ' alt="ИзображениеРецепта"></div>\n' +
                        ' <div><a href="recipe.html" class="nameRec" id=' + newIdNameRec + ' target="_self"> ' + data[i]["name"] + ' </a></div>\n' +
                        ' <div class="nameAuthor" id=' + newIdNameAuthor + '> Автор: ' + data[i]["userLogin"] + '</div>\n' +
                        ' <div class="timeCookingRec" id=' + newIdTimeCooking + '> Время приготовления: ' + data[i]["cookingTime"] + ' минут</div>\n' +
                        '</div>';

                    document.getElementById("SearchingRec").append(divId);

                    if (document.getElementById(newIdNameRec)) {
                        document.getElementById(newIdNameRec).addEventListener("click", () => setNameRec(nameRec));
                    }


                    document.getElementById(newIdBlockSearch).style.left = currLeftOff + 'px';
                    document.getElementById(newIdBlockSearch).style.top = currTopOff + 'px';

                    setCurrLeftOff(increasePerem(currLeftOff, 360));

                    if ((i !== 0) && ((i + 1) % 4 === 0)) {
                        setCurrTopOff(increasePerem(currTopOff, 430));
                        setCurrLeftOff(73);
                    }
                }
                document.cookie = "keyword=";
            }
        }, // обработка ответа от сервера
        error: function () {
        },
    });
}

function getSearchResByFilter(filter1, filter2){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/povarenok/recipes/' + filter1 + '/' + filter2,
        dataType: 'json', // тип ожидаемых данных,
        contentType: 'application/json',
        success: function (data) {
            if(data.length !== 0) {
                let countSearchBlock = data.length; /*ДОБАВИТЬ ПОЛУЧЕНИЕ ЧИСЛА НАЙДЕННЫХ РЕЦЕПТОВ*/

                document.getElementById('textCountRes').innerText = "ВСЕГО РЕЦЕПТОВ НАЙДЕНО: " + countSearchBlock;
                document.getElementById('textFilterRes').innerText = "фильтры: " + filter1 + ", " + filter2;

                setCurrLeftOff(73);
                setCurrTopOff(310);

                for (let i = 0; i < data.length; i++) {
                    let newIdRecImg = "recImage" + i;
                    let newIdNameRec = "nameRec" + i;
                    let newIdNameAuthor = "nameAuthor" + i;
                    let newIdTimeCooking = "timeCooking" + i;
                    let newIdBlockSearch = "blockSearch" + i;

                    let divId = document.createElement('div');
                    let nameRec = "\'" + data[i]["name"] + "\'";
                    let src = "../downloads/" + data[i]["imageUrl"];

                    divId.innerHTML = '<div class="blockSearchRec" id=' + newIdBlockSearch + '>\n' +
                        ' <div><img src="' + src + '" class="recImage" id=' + newIdRecImg + ' alt="ИзображениеРецепта"></div>\n' +
                        ' <div><a href="recipe.html" class="nameRec" id=' + newIdNameRec + ' target="_self"> ' + data[i]["name"] + ' </a></div>\n' +
                        ' <div class="nameAuthor" id=' + newIdNameAuthor + '> Автор: ' + data[i]["userLogin"] + '</div>\n' +
                        ' <div class="timeCookingRec" id=' + newIdTimeCooking + '> Время приготовления: ' + data[i]["cookingTime"] + ' минут</div>\n' +
                        '</div>';

                    document.getElementById("SearchingRec").append(divId);

                    if (document.getElementById(newIdNameRec)) {
                        document.getElementById(newIdNameRec).addEventListener("click",
                            () => setNameRec(nameRec));
                    }

                    document.getElementById(newIdBlockSearch).style.left = currLeftOff + 'px';
                    document.getElementById(newIdBlockSearch).style.top = currTopOff + 'px';

                    setCurrLeftOff(increasePerem(currLeftOff, 360));

                    if ((i !== 0) && ((i + 1) % 4 === 0)) {
                        setCurrTopOff(increasePerem(currTopOff, 430));
                        setCurrLeftOff(73);
                    }
                }
                document.cookie = "filter1=";
                document.cookie = "filter2=";
            }
        }, // обработка ответа от сервера
        error: function (data) {
        },
    });
}

let addedCat = false;
let addedCus = false;

function getRecentRec(countSearchBlock){
    setCurrLeftOff(73);

    $.ajax({
        type: 'GET',
        url: `http://localhost:8080/povarenok/recipes/last?count=${countSearchBlock}`, // адрес запроса
        dataType: 'json', // тип ожидаемых данных,
        contentType: 'application/json',
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                let newIdRecImg = "recImage" + i;
                let newIdNameRec = "nameRec" + i;
                let newIdNameAuthor = "nameAuthor" + i;
                let newIdTimeCooking = "timeCooking" + i;
                let newIdBlockSearch = "block" + i;

                let divId = document.createElement('div');

                let src = "../downloads/" + data[i]["imageUrl"];
                let nameRec = "\'" + data[i]["name"] + "\'" ;

                divId.innerHTML = '<div class="blockRec" id=' + newIdBlockSearch + '>\n' +
                    '  <div><img src="' + src + '" class="recImage" id=' + newIdRecImg + ' alt="ИзображениеРецепта"></div>\n' +
                    '  <div><a href="recipe.html" class="nameRec" id=' + newIdNameRec + ' target="_self">' + data[i]["name"] + '</a></div>\n' +
                    '  <div class="nameAuthor" id=' + newIdNameAuthor + '> Автор: ' + data[i]["userLogin"] + '</div>\n' +
                    '  <div class="timeCookingRec" id=' + newIdTimeCooking + '> Время приготовления: ' + data[i]["cookingTime"] + ' минут</div>\n' +
                    '</div>';

                document.getElementById("indexRec").appendChild(divId);

                if(document.getElementById(newIdNameRec)){
                    document.getElementById(newIdNameRec).addEventListener("click",
                        () => setNameRec(nameRec))
                }

                document.getElementById(newIdBlockSearch).style.left = currLeftOff + 'px';

                setCurrLeftOff(increasePerem(currLeftOff, 360));
            }
        }, // обработка ответа от сервера
        error: function (data) {
        },
    });
}

function addCatIndex(data){
    if (!addedCat) {
        for (let i = 0; i < data.length; i++) {
            let divCat = document.createElement('option');
            divCat.innerHTML = '<option value="' + data[i]["id"] + '">' + data[i]["name"] + '</option>';
            document.getElementById("categoryIndex").append(divCat);
            if (i === data.length - 1) {
                addedCat = true;
            }
        }
    }
}

function getCategInIndex(){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/povarenok/categories', // адрес запроса
        dataType: 'json', // тип ожидаемых данных,
        contentType: 'application/json',
        success: function (data) {
            addCatIndex(data);
        }, // обработка ответа от сервера
        error: function (data) {
        },
    });
}

function getCuisinesIndex(){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/povarenok/cuisines', // адрес запроса
        dataType: 'json', // тип ожидаемых данных,
        contentType: 'application/json',
        success: function (data) {
            if (!addedCus) {
                for (let i = 0; i < data.length; i++) {
                    let divCat = document.createElement('option');
                    divCat.innerHTML = '<option value="' + data[i]["id"] + '">' + data[i]["name"] + '</option>';
                    document.getElementById("typeCuis").append(divCat);
                    if (i === data.length - 1) {
                        addedCus = true;
                    }
                }
            }
        }, // обработка ответа от сервера
        error: function (data) {
        },
    });
}

function loadInfoIndexRec() {
    getRecentRec(4);
    getCategInIndex();
    getCuisinesIndex();
    setUserName();
}

function setNameRec(nameRec){
    document.cookie = "nameRec=" + nameRec;
}

let flag = false;

function loadInfoUserRec() {
    let username = document.cookie.match('(^|;) ?' + "username" + '=([^;]*)(;|$)');

    let divUserName = document.createElement('div');

    if (username) {
        divUserName.innerHTML = '<div id="textUserName">' + username[2] + '</div>';
    }

    document.getElementById("headerBlock").append(divUserName);

    if(!flag) {
        getUserSavedAndAddedRecipe(username[2]);
    }
}

let currTopOff;

function setCurrTopOff(val){
    currTopOff = val;
    return currTopOff;
}

function calcHeightBlock(val){
    return (70 + 230 * val);
}

function getUserSavedAndAddedRecipe(username){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/povarenok/users/' + username, // адрес запроса
        dataType: 'json', // тип ожидаемых данных,
        contentType: 'application/json',
        success: function (data) {
            if (data.length !== 0) {
                let countAddedRec = data["addedRecipes"].length; /*ДОБАВИТЬ ПОЛУЧЕНИЕ ЧИСЛА СОЗДАННЫХ ПОЛЬЗОВАТЕЛЕМ РЕЦЕПТОВ*/
                let countFavRec = data["savedRecipes"].length; /*ДОБАВИТЬ ПОЛУЧЕНИЕ ЧИСЛА ИЗБРАННЫХ РЕЦЕПТОВ*/

                setCurrTopOff(70);

                document.getElementById('blockUserRecOne').style.height =  calcHeightBlock(countAddedRec) + 'px';

                for (let i = 0; i < countAddedRec; i++) {
                    let newIdRecImg = "recImage" + i;
                    let newIdNameRec = "nameRec" + i;
                    let newIdNameAuthor = "nameAuthor" + i;
                    let newIdTimeCooking = "timeCooking" + i;
                    let newIdBlockUserRec = "subblockUserRecOne" + i;

                    let divId = document.createElement('div');

                    let src = "../downloads/" + data["addedRecipes"][i]["imageUrl"];
                    let nameRec = "\'" + data["addedRecipes"][i]["name"] + "\'";

                    divId.innerHTML = '<div class="subblockUserRec" id=' + newIdBlockUserRec + '>\n' +
                        ' <div><img src="' + src + '" class="recImage" id=' + newIdRecImg + ' alt="ИзображениеРецепта"></div>\n' +
                        ' <div><a href="recipe.html" class="nameUserRec" id=' + newIdNameRec + ' target="_self">' + data["addedRecipes"][i]["name"] + '</a></div>\n' +
                        ' <div class="nameAuthorRec" id=' + newIdNameAuthor + '> Автор: ' + data["addedRecipes"][i]["userLogin"] + '</div>\n' +
                        ' <div class="UserTimeCooking" id=' + newIdTimeCooking + '> Время приготовления: ' + data["addedRecipes"][i]["cookingTime"] + ' минут</div>\n' +
                        '</div>';

                    document.getElementById("blockUserRecOne").append(divId);

                    if (document.getElementById(newIdNameRec)) {
                        document.getElementById(newIdNameRec).addEventListener("click",
                            () => setNameRec(nameRec));
                    }

                    document.getElementById(newIdBlockUserRec).style.top = currTopOff + 'px';

                    document.getElementById(newIdRecImg).style.top = 5 + 'pt';
                    document.getElementById(newIdRecImg).style.left = 10 + 'pt';

                    setCurrTopOff(increasePerem(currTopOff, 230));
                }

                setCurrTopOff(70);
                document.getElementById('blockUserRecTwo').style.height =
                    calcHeightBlock(countFavRec) + 'px';

                for (let i = 0; i < countFavRec; i++) {
                    let newIdRecImg = "recImageFav" + i + countAddedRec;
                    let newIdNameRec = "nameRecFav" + i + countAddedRec;
                    let newIdNameAuthor = "nameAuthorFav" + i + countAddedRec;
                    let newIdTimeCooking = "timeCookingFav" + i + countAddedRec;
                    let newIdBlockUserRec = "subblockUserRecOneFav" + i + countAddedRec;

                    let divId = document.createElement('div');

                    let src = "../downloads/" + data["savedRecipes"][i]["imageUrl"];
                    let nameRec = "\'" + data["savedRecipes"][i]["name"] + "\'";

                    divId.innerHTML = '<div class="subblockUserRec" id=' + newIdBlockUserRec + '>\n' +
                        ' <div><img src="' + src + '" class="recImage" id=' + newIdRecImg + ' alt="ИзображениеРецепта"></div>\n' +
                        ' <div><a href="recipe.html" class="nameUserRec" id=' + newIdNameRec + ' target="_self">' + data["savedRecipes"][i]["name"] + '</a></div>\n' +
                        ' <div class="nameAuthorRec" id=' + newIdNameAuthor + '> Автор: ' + data["savedRecipes"][i]["userLogin"] + '</div>\n' +
                        ' <div class="UserTimeCooking" id=' + newIdTimeCooking + '> Время приготовления: ' + data["savedRecipes"][i]["cookingTime"] + ' минут</div>\n' +
                        '</div>';

                    document.getElementById("blockUserRecTwo").append(divId);

                    if (document.getElementById(newIdNameRec)) {
                        document.getElementById(newIdNameRec).addEventListener("click",
                            () => setNameRec(nameRec));
                    }

                    document.getElementById(newIdBlockUserRec).style.top = currTopOff + 'px';

                    document.getElementById(newIdRecImg).style.top = 5 + 'pt';
                    document.getElementById(newIdRecImg).style.left = 10 + 'pt';

                    setCurrTopOff(increasePerem(currTopOff, 230));
                }
                flag = true;
            }
        }, // обработка ответа от сервера
        error: function (data) {
        },
    });
}

//запрос сохраняет новый рецепт в базу данных исполь в файлк addRecipe.html
function postAddReq() {
    let dateTime = new Date();
    let dateNew = dateTime.toISOString().split('T')[0];

    let url = Date.parse(new Date() + '') + '.png';

    const link = document.createElement('a'); // создаём ссылку
    link.href = document.querySelector('.images').querySelector('img').getAttribute('src');
    link.setAttribute('download', url); // чтобы при скачивании файл назывался как нам нужно - указываем явно имя в атрибут ссылки
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);

    let ingredients = [];
    let div = document.querySelectorAll('.addIngRec');

    for (let i = 1; i < div.length + 1; i++) {

        let newIdIng = `inputIngRec${i}`;
        let newIdGr = `inputGrRec${i}`;

        let ingredient = {
            idRecipe: null,
            name: document.getElementById(newIdIng).value,
            grams: document.getElementById(newIdGr).value
        };

        ingredients.push(ingredient);
    }

    setNewReq({
        userLogin: document.cookie.match('(^|;) ?' + "username" + '=([^;]*)(;|$)')[2],
        name: document.getElementById('inputNameRec').value,
        imageUrl: url,
        dateAdded: dateNew,
        cuisine: document.getElementById('typeCuisAddRec').value,
        category: document.getElementById('categoryAddRec').value,
        cookingTime: document.getElementById('inputTimeCook').value,
        ingredients: ingredients,
        description: document.getElementById('inputDecRec').value,
        recipe: document.getElementById('inputStepRec').value
    });
}

function setNewReq(rec){
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/povarenok/recipes/new', // адрес запроса
        data: JSON.stringify(rec), // данные запроса
        dataType: 'json', // тип ожидаемых данных,
        contentType: 'application/json',
        success: function (data) {
        }, // обработка ответа от сервера
        error: function (data) {
        },
    });
}

//запрос сохраняет пользователя в базу данных
function postSignUpReq() {
    setNewUser({
        login: document.getElementById('signUpLogin').value,
        password: document.getElementById('signUpPassword').value,
        email: document.getElementById('signUpEmail').value
    });
}

function setNewUser(user){
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/povarenok/registration', // адрес запроса
        data: JSON.stringify(user), // данные запроса
        dataType: 'json', // тип ожидаемых данных,
        contentType: 'application/json',
        success: function (data) {
        }, // обработка ответа от сервера
        error: function (data) {
        },
    });
}

//запрос сохраняет рецепт с указанным наименованием в избранные рецепты пользователя с указанным логином
function postSaveReq() {
    if (document.getElementById('addFavorites').innerText === "Добавить в избранное") {
        document.getElementById('addFavorites').innerText = "Удалить из избранного";

        //------раскоменитить когда будут готова реализация обработки пробелов

        addRecInFav(document.cookie.match('(^|;) ?' + "username" + '=([^;]*)(;|$)')[2],
            document.getElementById('infoNameRec').value);

    } else if (document.getElementById('addFavorites').innerText === "Удалить из избранного") {
        document.getElementById('addFavorites').innerText = "Добавить в избранное";

        //------раскоменитить когда будут готова реализация обработки пробелов

        deleteRecFromFav(document.cookie.match('(^|;) ?' + "username" + '=([^;]*)(;|$)')[2],
            document.getElementById('infoNameRec').value);
    }

}

function addRecInFav(login, name){
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/povarenok/recipes/' + login + '/save/' + name, // адрес запроса
        dataType: 'json', // тип ожидаемых данных,
        contentType: 'application/json',
        success: function (data) {

        }, // обработка ответа от сервера
        error: function (data) {
        },
    });
}

function deleteRecFromFav(login, name){
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/povarenok/recipes/' + login + '/delete/' + name, // адрес запроса
        dataType: 'json', // тип ожидаемых данных,
        contentType: 'application/json',
        success: function (data) {
        }, // обработка ответа от сервера
        error: function (data) {
        },
    });
}

module.exports = {addIngBlock, toggleButtonAddRec, toggleButtonSignIn,
        toggleButtonSignUp, searchFilters, searchKeywords, isCorrectSignIn, isCorrectSignUp,
        changeHeightRec, dragAndDropFunc, loadInfoIndexRec, loadInfoUserRec, loadInfoSearchRec, getCategInIndex,
        getCuisinesIndex, getRecentRec, postAddReq, postSignUpReq, postSaveReq, $, getRecipeByName,
        getSavedRecByUserName, getUserSavedAndAddedRecipe, setNewReq, setNewUser, getCategAddRecHtml, getCuisinesAddRecHtml,
        getSearchResByKeyword, getSearchResByFilter, addRecInFav, deleteRecFromFav, validRecValue,
        validSignInValue, validSignUpValue, setCurrTopOff, calcHeightBlock, setCurrLeftOff, setCurrTopIng, increasePerem};

