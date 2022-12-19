let indexBlockIng = 1;
let currTopIng = 381;
let imageTmpUrl;

function deleteIngBlock(id){
  document.getElementById("inputIngRec" + id).remove();
  document.getElementById("inputGrRec" + id).remove();
  document.getElementById("deleteIng" + id).remove();

  document.getElementById('addIngButton').style.top = document.getElementById('addIngButton').offsetTop - 50 + 'px';
  document.getElementById('addIngText').style.top = document.getElementById('addIngText').offsetTop - 50 + 'px';
  document.getElementById('lineAddIngRec').style.top = document.getElementById('lineAddIngRec').offsetTop - 50 + 'px';
  document.getElementById('inputStepRec').style.top = document.getElementById('inputStepRec').offsetTop - 50 + 'px';


  for(let i = Number(id) + 1; i <= indexBlockIng; i++){
    let newIdIng = "inputIngRec" + i;
    let newIdGr = "inputGrRec" + i;
    let newIdImg = "deleteIng" + i;

    if(document.getElementById(newIdIng)){
      document.getElementById(newIdIng).style.top = document.getElementById(newIdIng).offsetTop - 50 + 'px';
      document.getElementById(newIdGr).style.top = document.getElementById(newIdGr).offsetTop - 50 + 'px';
      document.getElementById(newIdImg).style.top =  document.getElementById(newIdImg).offsetTop - 50 + 'px';
    }
  }

  toggleButtonAddRec();
}

function addBlock(){
  document.getElementById('createRecButton').disabled = true;
  for(let i = 1; i <= indexBlockIng; i++){
    let newIdIng = "inputIngRec" + i;

    if(document.getElementById(newIdIng)){
      currTopIng = document.getElementById(newIdIng).offsetTop;
    }
  }

  currTopIng += 50;
  indexBlockIng++;

  let newIdIng = "inputIngRec" + indexBlockIng;
  let newIdGr = "inputGrRec" + indexBlockIng;
  let newIdImg = "deleteIng" + indexBlockIng;

  let divId = document.createElement('div');
  divId.innerHTML = '<input class="addIngRec" type="text" ' +
    'placeholder="Добавьте название ингредиента" minlength="2" maxlength="100" required\n' +
    '            oninput="this.value = this.value.replace(/[^а-яёЁ\\s]/gi, \'\');"\n' +
    '            id=' + newIdIng + ' onchange="toggleButtonAddRec()">';

  let divGr = document.createElement('div');
  divGr.innerHTML = '<div>\n' +
    '        <input class="addGrRec" type="text" placeholder="граммы" minlength="1" maxlength="5" required\n' +
    '               oninput="this.value = this.value.replace(/[^0-9\\s]/gi, \'\');"\n' +
    '               id=' + newIdGr + ' onchange="toggleButtonAddRec()">\n' +
    '      </div>';

  let divImg = document.createElement('div');
  divImg.innerHTML = '<div>\n' +
    '        <button type="button" class="deleteIngButton" id=' + newIdImg + '>\n' +
    '          <img src="../img/deleteIng.png" class="deleteIngImg" alt="Удалить ингредиент" ' +
    'onclick="deleteIngBlock('+ indexBlockIng + ')">\n' +
    '        </button>\n' +
    '      </div>';

  document.getElementById("addedIngRec").append(divId);
  document.getElementById("addedIngRec").append(divGr);
  document.getElementById("addedIngRec").append(divImg);

  document.getElementById(newIdIng).style.top = currTopIng + 'px';
  document.getElementById(newIdGr).style.top = currTopIng + 'px';
  document.getElementById(newIdImg).style.top = currTopIng + 11 + 'px';
}

function addIngBlock(){
  addBlock();

  document.getElementById('addIngButton').style.top = document.getElementById('addIngButton').offsetTop + 50 + 'px';
  document.getElementById('addIngText').style.top = document.getElementById('addIngText').offsetTop + 50 + 'px';
  document.getElementById('lineAddIngRec').style.top = document.getElementById('lineAddIngRec').offsetTop + 50 + 'px';
  document.getElementById('inputStepRec').style.top = document.getElementById('inputStepRec').offsetTop + 50 + 'px';
}

function toggleButtonAddRec()
{
  let nameRec = document.getElementById('inputNameRec').value;
  let DecRec = document.getElementById('inputDecRec').value;

  let inputIngRecOne = document.getElementById('inputIngRec1').value;

  let inputGrRecOne = document.getElementById('inputGrRec1').value;

  let inputStepRec = document.getElementById('inputStepRec').value;
  let inputTimeCook = document.getElementById('inputTimeCook').value;

  let typeCuisAddRec = document.getElementById('typeCuisAddRec');
  let categoryAddRec = document.getElementById('categoryAddRec');

  document.getElementById('createRecButton').disabled = !((nameRec.length > 1) && (DecRec.length > 1)
    && (inputIngRecOne.length > 1) && (inputGrRecOne.length > 0)
    && (inputStepRec.length > 20) && (inputTimeCook.length > 0)
    && !(typeCuisAddRec.selectedIndex == 0) && !(categoryAddRec.selectedIndex == 0) && (imageTmpUrl != ""));

  for(let i = 1; i <= indexBlockIng; i++){
    let newIdIng = "inputIngRec" + i;
    let newIdGr = "inputGrRec" + i;

    if(document.getElementById(newIdIng)){
      console.log(document.getElementById(newIdIng).value.length);

      if((document.getElementById(newIdIng).value.length < 2)
        || (document.getElementById(newIdGr).value.length == 0)){
        document.getElementById('createRecButton').disabled = true;
      }
    }
  }
}

function toggleButtonSignIn()
{
  let username = document.getElementById('inputLogin').value;
  let password = document.getElementById('inputPassword').value;

  document.getElementById('enterButton').disabled = !((username.length > 1) && (password.length > 7));
}

function remEvent(event) {
  event.preventDefault();
  alert("Пароль или Логин введены неверно");
}

function isCorrectSignIn() {
  let username = document.getElementById("inputLogin").value;
  let password = document.getElementById("inputPassword").value;
  document.removeEventListener('submit', remEvent);

  if ((password.length < 8) || (password.length > 12)
    || (username.length < 2) || (username.length > 30)){
    document.addEventListener('submit', remEvent );
  } /*Добавить проверку, существует ли такой пользователь и корректность данных*/
}

function changeHeightRec() {
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

  document.getElementById('addFavorites').disabled = !(document.getElementById('signInButton').innerText != "Войти");
}

function changeNameButton(){
  console.log(document.getElementById('addFavorites').innerText);
  if(document.getElementById('addFavorites').innerText == "Добавить в избранное"){
    document.getElementById('addFavorites').innerText = "Удалить из избранного";
  }
  else if(document.getElementById('addFavorites').innerText == "Удалить из избранного"){
    document.getElementById('addFavorites').innerText = "Добавить в избранное";
  }
}

function toggleButtonSignUp()
{
  let username = document.getElementById('signUpLogin').value;
  let password = document.getElementById('signUpPassword').value;
  let email = document.getElementById('signUpEmail').value;

  document.getElementById('registerButton').disabled = !((username.length > 1) && (password.length > 7)
    && (email.length > 0));
}

function isCorrectSignUp() {
  /*Добавить проверку, существует ли такой пользователь */
}

let files;
let index;

let addedCatAddRec = false;
let addedCusAddRec = false;

function dragAndDropFunc(){
  const types = ['image/jpeg', 'image/png'];
  imageTmpUrl = "";
  let dragAndDrop = document.querySelector('.dropImgZone'),
    images = document.querySelector('.images');

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
    if (types.includes(files[e.dataTransfer.files.length - 1].type)){
      if(document.getElementById("addImg")){
        document.getElementById("addImg").remove();
      }
      if(document.getElementById("addImgRecText")){
        document.getElementById("addImgRecText").remove();
      }

      imageTmpUrl = URL.createObjectURL(files[e.dataTransfer.files.length - 1]);
      images.innerHTML = `<img src="${imageTmpUrl}" class="image" alt="">`;
      toggleButtonAddRec();
    }

    dragAndDrop.classList.remove('active');
  })

  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/povarenok/categories', // адрес запроса
    dataType: 'json', // тип ожидаемых данных,
    contentType: 'application/json',
    success: function(data) {
      if(!addedCatAddRec) {
        for (let i = 0; i < data.length; i++) {
          let divCat = document.createElement('option');
          divCat.innerHTML = '<option value="' + data[i]["id"] + '">' + data[i]["name"] + '</option>';
          document.getElementById("categoryAddRec").append(divCat);
          if (i == data.length - 1) {
            addedCatAddRec = true;
          }
        }
      }
      console.log(data);
    }, // обработка ответа от сервера
    error: function(data) { console.log(data); },
  });

  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/povarenok/cuisines', // адрес запроса
    dataType: 'json', // тип ожидаемых данных,
    contentType: 'application/json',
    success: function(data) {
      if(!addedCusAddRec) {
        for (let i = 0; i < data.length; i++) {
          let divCat = document.createElement('option');
          divCat.innerHTML = '<option value="' + data[i]["id"] + '">' + data[i]["name"] + '</option>';
          document.getElementById("typeCuisAddRec").append(divCat);
          if (i == data.length - 1) {
            addedCusAddRec = true;
          }
        }
      }
      console.log(data);
    }, // обработка ответа от сервера
    error: function(data) { console.log(data); },
  });
}

function uploadImages() {
}

function loadInfoSearchRec(){
  let countSearchBlock = 7; /*ДОБАВИТЬ ПОЛУЧЕНИЕ ЧИСЛА НАЙДЕННЫХ РЕЦЕПТОВ*/

  document.getElementById('textCountRes').innerText = "ВСЕГО РЕЦЕПТОВ НАЙДЕНО: " + countSearchBlock;
  //document.getElementById('textFilterRes').innerText = "фильтры: " + /*ДОБАВИТЬ ПОЛУЧЕНИЕ ФИЛЬТРОВ*/;

  let currLeftOff = 73;
  let currTopOff = 310;

  for(let i = 0; i < countSearchBlock; i++){
    let newIdRecImg = "recImage" + i;
    let newIdNameRec = "nameRec" + i;
    let newIdNameAuthor = "nameAuthor" + i;
    let newIdTimeCooking = "timeCooking" + i;
    let newIdBlockSearch = "blockSearch" + i;

    let divId = document.createElement('div');

    divId.innerHTML = '<div class="blockSearchRec" id=' + newIdBlockSearch + '>\n' +
      '  <div><img src="../img/exampleRec.jpg" class="recImage" id=' + newIdRecImg + ' alt="Изображение рецепта"></div>\n' +
      '  <div><a href="recipe.html" class="nameRec" id=' + newIdNameRec + ' target="_self"> Киш с курицей и сыром </a></div>\n' +
      '  <div class="nameAuthor" id=' + newIdNameAuthor + '> Автор: nkazimirov</div>\n' +
      '  <div class="timeCookingRec" id=' + newIdTimeCooking + '> Время приготовления: 1 час 20 минут</div>\n' +
      '</div>';

    document.getElementById("SearchingRec").append(divId);

    document.getElementById(newIdBlockSearch).style.left = currLeftOff + 'px';
    document.getElementById(newIdBlockSearch).style.top = currTopOff + 'px';

    currLeftOff += 360;

    if((i != 0) && ((i + 1) % 4 == 0)){
      currTopOff += 430;
      currLeftOff = 73;
    }
  }
}

let addedCat = false;
let addedCus = false;

function loadInfoIndexRec(){
  let countSearchBlock = 4; /*ДОБАВИТЬ ПОЛУЧЕНИЕ ЧИСЛА НЕДАВНО ДОБАВЛЕННЫХ РЕЦЕПТОВ, максимум 4, но может быть меньше*/
  let currLeftOff = 73;

  for(let i = 0; i < countSearchBlock; i++){
    let newIdRecImg = "recImage" + i;
    let newIdNameRec = "nameRec" + i;
    let newIdNameAuthor = "nameAuthor" + i;
    let newIdTimeCooking = "timeCooking" + i;
    let newIdBlockSearch = "block" + i;

    let divId = document.createElement('div');

    divId.innerHTML = '<div class="blockRec" id=' + newIdBlockSearch + '>\n' +
      '  <div><img src="../img/exampleRec.jpg" class="recImage" id=' + newIdRecImg + ' alt="Изображение рецепта"></div>\n' +
      '  <div><a href="recipe.html" class="nameRec" id=' + newIdNameRec + ' target="_self"> Киш с курицей и сыром </a></div>\n' +
      '  <div class="nameAuthor" id=' + newIdNameAuthor + '> Автор: nkazimirov</div>\n' +
      '  <div class="timeCookingRec" id=' + newIdTimeCooking + '> Время приготовления: 1 час 20 минут</div>\n' +
      '</div>';

    document.getElementById("indexRec").append(divId);

    document.getElementById(newIdBlockSearch).style.left = currLeftOff + 'px';

    currLeftOff += 360;


    $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/povarenok/categories', // адрес запроса
      dataType: 'json', // тип ожидаемых данных,
      contentType: 'application/json',
      success: function(data) {
        if(!addedCat) {
          for (let i = 0; i < data.length; i++) {
            let divCat = document.createElement('option');
            divCat.innerHTML = '<option value="' + data[i]["id"] + '">' + data[i]["name"] + '</option>';
            document.getElementById("categoryIndex").append(divCat);
            if (i == data.length - 1) {
              addedCat = true;
            }
          }
        }
        console.log(data);
        }, // обработка ответа от сервера
      error: function(data) { console.log(data); },
    });

    $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/povarenok/cuisines', // адрес запроса
      dataType: 'json', // тип ожидаемых данных,
      contentType: 'application/json',
      success: function(data) {
        if(!addedCus) {
          for (let i = 0; i < data.length; i++) {
            let divCat = document.createElement('option');
            divCat.innerHTML = '<option value="' + data[i]["id"] + '">' + data[i]["name"] + '</option>';
            document.getElementById("typeCuis").append(divCat);
            if (i == data.length - 1) {
              addedCus = true;
            }
          }
        }
        console.log(data);
      }, // обработка ответа от сервера
      error: function(data) { console.log(data); },
    });
  }
}

function loadInfoUserRec(){
  let countAddedRec = 4; /*ДОБАВИТЬ ПОЛУЧЕНИЕ ЧИСЛА СОЗДАННЫХ ПОЛЬЗОВАТЕЛЕМ РЕЦЕПТОВ*/
  let countFavRec = 2; /*ДОБАВИТЬ ПОЛУЧЕНИЕ ЧИСЛА ИЗБРАННЫХ РЕЦЕПТОВ*/

  let currTopOff = 70;

  let heightBlockUserRecOne = 70 + 230 * countAddedRec;
  document.getElementById('blockUserRecOne').style.height = heightBlockUserRecOne + 'px';
  for(let i = 0; i < countAddedRec; i++){
    let newIdRecImg = "recImage" + i;
    let newIdNameRec = "nameRec" + i;
    let newIdNameAuthor = "nameAuthor" + i;
    let newIdTimeCooking = "timeCooking" + i;
    let newIdBlockUserRec = "subblockUserRecOne" + i;

    let divId = document.createElement('div');

    divId.innerHTML = '<div class="subblockUserRec" id=' + newIdBlockUserRec + '>\n' +
      '  <div><img src="../img/exampleRec.jpg" class="recImage" id=' + newIdRecImg + ' alt="Изображение рецепта"></div>\n' +
      '  <div><a href="recipe.html" class="nameUserRec" id=' + newIdNameRec + ' target="_self"> Киш с курицей и сыром </a></div>\n' +
      '  <div class="nameAuthorRec" id=' + newIdNameAuthor + '> Автор: nkazimirov</div>\n' +
      '  <div class="UserTimeCooking" id=' + newIdTimeCooking + '> Время приготовления: 1 час 20 минут</div>\n' +
      '</div>';

    document.getElementById("blockUserRecOne").append(divId);

    document.getElementById(newIdBlockUserRec).style.top = currTopOff + 'px';

    document.getElementById(newIdRecImg).style.top = 5 + 'pt';
    document.getElementById(newIdRecImg).style.left = 10 + 'pt';

    currTopOff += 230;
  }

  currTopOff = 70;
  let heightBlockUserRecTwo = 70 + 230 * countFavRec;
  document.getElementById('blockUserRecTwo').style.height = heightBlockUserRecTwo + 'px';

  for(let i = countAddedRec; i < countFavRec + countAddedRec; i++){
    let newIdRecImg = "recImage" + i;
    let newIdNameRec = "nameRec" + i;
    let newIdNameAuthor = "nameAuthor" + i;
    let newIdTimeCooking = "timeCooking" + i;
    let newIdBlockUserRec = "subblockUserRecOne" + i;

    let divId = document.createElement('div');

    divId.innerHTML = '<div class="subblockUserRec" id=' + newIdBlockUserRec + '>\n' +
      '  <div><img src="../img/exampleRec.jpg" class="recImage" id=' + newIdRecImg + ' alt="Изображение рецепта"></div>\n' +
      '  <div><a href="recipe.html" class="nameUserRec" id=' + newIdNameRec + ' target="_self"> Киш с курицей и сыром </a></div>\n' +
      '  <div class="nameAuthorRec" id=' + newIdNameAuthor + '> Автор: nkazimirov</div>\n' +
      '  <div class="UserTimeCooking" id=' + newIdTimeCooking + '> Время приготовления: 1 час 20 минут</div>\n' +
      '</div>';

    document.getElementById("blockUserRecTwo").append(divId);

    document.getElementById(newIdBlockUserRec).style.top = currTopOff + 'px';

    document.getElementById(newIdRecImg).style.top = 5 + 'pt';
    document.getElementById(newIdRecImg).style.left = 10 + 'pt';

    currTopOff += 230;
  }
}

//запрос сохраняет новый рецепт в базу данных исполь в файлк addRecipe.html
function postAddReq() {
  let dateTime = new Date();
  let dateNew = dateTime.toISOString().split('T')[0];
  console.log(dateNew);

  var userLogin = "test24";
  var name = document.getElementById('inputNameRec').value;
  var imageUrl = "url47";
  var dateAdded = dateNew;
  var cuisine = document.getElementById('typeCuisAddRec').value;
  var category = document.getElementById('categoryAddRec').value;
  var cookingTime = document.getElementById('inputTimeCook').value;
  var description = document.getElementById('inputDecRec').value;
  var recipe = document.getElementById('inputStepRec').value;


  let ingredients = [];
  let div = document.querySelectorAll('.addIngRec');
  console.log(div);

  for(let i = 1; i < div.length + 1; i++) {

    let newIdIng = `inputIngRec${i}`;
    let newIdGr = `inputGrRec${i}`;

    let ingredient = {
      idRecipe: null,
      name: document.getElementById(newIdIng).value,
      grams: document.getElementById(newIdGr).value
    };

    ingredients.push(ingredient);
  }

  console.log(ingredients);

  let rec = {
    userLogin: userLogin,
    name: name,
    imageUrl: imageUrl,
    dateAdded: dateAdded,
    cuisine: cuisine,
    category: category,
    cookingTime: cookingTime,
    ingredients: ingredients,
    description: description,
    recipe: recipe
  }

  console.log(JSON.stringify(rec));

  $.ajax({
    type: 'POST',
    url: 'http://localhost:8080/povarenok/recipes/new', // адрес запроса
    data: JSON.stringify(rec), // данные запроса
    dataType: 'json', // тип ожидаемых данных,
    contentType: 'application/json',
    success: function (data) {
      console.log(data);
    }, // обработка ответа от сервера
    error: function (data) {
      console.log(data);
    },
  });
}

//запрос сохраняет пользователя в базу данных
function postSignUpReq() {
  var login = document.getElementById('signUpLogin').value;
  var pass = document.getElementById('signUpPassword').value;
  var email = document.getElementById('signUpEmail').value;

  let user = {
    login: login,
    password: pass,
    email: email
  }

  $.ajax({
    type: 'POST',
    url: 'http://localhost:8080/povarenok/registration', // адрес запроса
    data: JSON.stringify(user), // данные запроса
    dataType: 'json', // тип ожидаемых данных,
    contentType: 'application/json',
    success: function(data) { console.log(data); }, // обработка ответа от сервера
    error: function(data) { console.log(data); },
  });
}

//запрос сохраняет рецепт с указанным наименованием в избранные рецепты пользователя с указанным логином
function postSaveReq(){
  var login = "test27"; // -----придумать как хранить пользователя
  var name = "Морепродукты";  //document.getElementById('infoNameRec').value; ------раскоменитить когда будут готова реализация обработки пробелов
  $.ajax({
    type: 'POST',
    url: 'http://localhost:8080/povarenok/recipes/'+ login + '/save/' + name, // адрес запроса
    dataType: 'json', // тип ожидаемых данных,
    contentType: 'application/json',
    success: function(data) { console.log(data); }, // обработка ответа от сервера
    error: function(data) { console.log(data); },
  });
}




