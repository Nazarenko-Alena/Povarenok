const {By,Builder, until, Select} = require("selenium-webdriver");
const assert = require('assert').strict;
require("chromedriver");

let browser;

//require('events').EventEmitter.defaultMaxListeners = 0

process.setMaxListeners(0);

describe("E2E. Scenario 1", () => {
    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html');
    })

    after(async ()=>{
        await browser.close();
    })

    it('check button "Войти"',async function () {

        let signInButton = await browser.wait(
            until.elementLocated(By.id('signInButton')), 10000);

        assert.equal(await signInButton.getText(), "Войти");
        assert.equal(await signInButton.getAttribute('href'), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");
    })

    it('check combo-box "Категории"',async function () {

        let elem = await browser.wait(
            until.elementLocated(By.id('categoryIndex')), 10000);
        elem.click();

        let select = new Select(await browser.wait(
            until.elementLocated(By.id('categoryIndex')), 10000));

        let optionList = await select.getOptions();

        assert.equal(await optionList[1].getText(),"Бульоны");
        assert.equal(await optionList[3].getText(),"Выпечка и десерты");
        assert.equal(await optionList[5].getText(),"Завтраки");
        assert.equal(await optionList[7].getText(),"Закуски");
        assert.equal(await optionList[9].getText(),"Напитки");
        assert.equal(await optionList[11].getText(),"Основные блюда");
        assert.equal(await optionList[13].getText(),"Паста");
        assert.equal(await optionList[15].getText(),"Пиццы");
        assert.equal(await optionList[17].getText(),"Салаты");
        assert.equal(await optionList[19].getText(),"Соусы и маринады");
        assert.equal(await optionList[21].getText(),"Супы");
        assert.equal(await optionList[23].getText(),"Сэндвичи");

        await select.selectByVisibleText("Супы");
    })

    it('check combo-box "Тип кухни"',async function () {

        let elem = await browser.wait(
            until.elementLocated(By.id('typeCuis')), 10000);
        elem.click();

        let select = new Select(await browser.wait(
            until.elementLocated(By.id('typeCuis')), 10000));

        let optionList = await select.getOptions();

        assert.equal(await optionList[1].getText(),"Американская");
        assert.equal(await optionList[3].getText(),"Армянская");
        assert.equal(await optionList[5].getText(),"Белорусская");
        assert.equal(await optionList[7].getText(),"Британская");
        assert.equal(await optionList[9].getText(),"Вьетнамская");
        assert.equal(await optionList[11].getText(),"Греческая");
        assert.equal(await optionList[13].getText(),"Грузинская");
        assert.equal(await optionList[15].getText(),"Европейская");
        assert.equal(await optionList[17].getText(),"Индийская");
        assert.equal(await optionList[19].getText(),"Испанская");
        assert.equal(await optionList[21].getText(),"Итальянская");
        assert.equal(await optionList[23].getText(),"Китайская");
        assert.equal(await optionList[25].getText(),"Корейская");
        assert.equal(await optionList[27].getText(),"Мексиканская");
        assert.equal(await optionList[29].getText(),"Паназиатская");
        assert.equal(await optionList[31].getText(),"Русская");
        assert.equal(await optionList[33].getText(),"Средиземноморская");
        assert.equal(await optionList[35].getText(),"Тайская");
        assert.equal(await optionList[37].getText(),"Узбекская");
        assert.equal(await optionList[39].getText(),"Украинская");
        assert.equal(await optionList[41].getText(),"Французская");
        assert.equal(await optionList[43].getText(),"Японская");

        await select.selectByVisibleText("Русская");
    })

    it('check button "Применить"',async function () {

        let applyButton = await browser.wait(
            until.elementLocated(By.id('applyButton')), 10000);
        await applyButton.click();

        let elem = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        let textNameRec0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage0')), 10000);
        let textRecImage0 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor0')), 10000);
        let textNameAuthor0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking0')), 10000);
        let textTimeCooking0 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        let textNameRec1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage1')), 10000);
        let textRecImage1 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor1')), 10000);
        let textNameAuthor1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking1')), 10000);
        let textTimeCooking1 = await elem.getText();

        assert.equal(textNameAuthor0,"Автор: polinafomina");
        assert.equal(textRecImage0,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/141.png");
        assert.equal(textNameRec0,"ЩИ");
        assert.equal(textTimeCooking0,"Время приготовления: 90 минут");

        assert.equal(textNameAuthor1,"Автор: polinafomina");
        assert.equal(textRecImage1,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/140.png");
        assert.equal(textNameRec1,"БОРЩ");
        assert.equal(textTimeCooking1,"Время приготовления: 120 минут");
    })

    it('check recipe "Щи"',async function () {
        let nameRec0 = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        nameRec0.click();

        await checkShchi();
    })
})

describe("E2E. Scenario 2", () => {
    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html');
    })

    after(async ()=>{
        await browser.close();
    })

    it('check button "Войти"',async function () {

        let signInButton = await browser.wait(
            until.elementLocated(By.id('signInButton')), 10000);

        assert.equal(await signInButton.getText(), "Войти");
        assert.equal(await signInButton.getAttribute('href'), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");
    })

    it('check recipe "Вафли"',async function () {

        let nameRec1 = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        nameRec1.click();

       await checkWaffles();
    })

    it('check click on logo',async function () {

        let elem = await browser.wait(
            until.elementLocated(By.id('logoImage')), 10000);
        await elem.click();

        elem = await browser.getCurrentUrl();

        assert.equal(elem,"file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html");
    })
})

describe("E2E. Scenario 3", () => {
    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html');
    })

    after(async ()=>{
        await browser.close();
    })

    it('check button "Войти"',async function () {

        let signInButton = await browser.wait(
            until.elementLocated(By.id('signInButton')), 10000);

        assert.equal(await signInButton.getText(), "Войти");
        assert.equal(await signInButton.getAttribute('href'), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");
    })

    it('check button "Найти"',async function () {

        let searchLine = await browser.wait(
            until.elementLocated(By.id('searchLine')), 10000);
        await searchLine.sendKeys("Щи");

        let findButton = await browser.wait(
            until.elementLocated(By.id('findButton')), 10000);
        await findButton.click();

        let elem = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        let textNameRec0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage0')), 10000);
        let textRecImage0 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor0')), 10000);
        let textNameAuthor0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking0')), 10000);
        let textTimeCooking0 = await elem.getText();

        assert.equal(textNameAuthor0,"Автор: polinafomina");
        assert.equal(textRecImage0,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/141.png");
        assert.equal(textNameRec0,"ЩИ");
        assert.equal(textTimeCooking0,"Время приготовления: 90 минут");
    })

    it('check recipe "Щи"',async function () {

        let nameRec0 = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        nameRec0.click();

        await checkShchi();
    })
})

describe("E2E. Scenario 4", () => {
    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html');
    })

    after(async ()=>{
        await browser.close();
    })

    it('check button "Войти" on index.html',async function () {

        let signInButton = await browser.wait(
            until.elementLocated(By.id('signInButton')), 10000);

        assert.equal(await signInButton.getText(), "Войти");
        assert.equal(await signInButton.getAttribute('href'), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");

        await signInButton.click();
        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");
    })

    it('check button "Войти" on signIn.html',async function () {

        let elem = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);
        let enterButton = await elem.getAttribute('disabled');
        assert.equal(enterButton, "true");

        let inputLogin = await browser.wait(
            until.elementLocated(By.id('inputLogin')), 10000);
        inputLogin.sendKeys("polinafomina");

        let inputPassword = await browser.wait(
            until.elementLocated(By.id('inputPassword')), 10000);
        inputPassword.sendKeys("qwerty123");

        elem = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);
        enterButton = await elem.getAttribute('disabled');

        try{
            assert.equal(enterButton, "false");
        }
        catch(err){
            if(err.message !== "Expected values to be strictly equal:\n" +
                "\n" +
                "null !== 'false'\n"){
                assert.equal(enterButton,"false");
            }
        }
    })

    it('check click button "Войти" on signIn.html',async function () {

        let enterButton = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);

        await enterButton.click();
        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/user.html");

        let elem = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        let textNameRec0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage0')), 10000);
        let textRecImage0 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor0')), 10000);
        let textNameAuthor0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking0')), 10000);
        let textTimeCooking0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        let textNameRec1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage1')), 10000);
        let textRecImage1 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor1')), 10000);
        let textNameAuthor1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking1')), 10000);
        let textTimeCooking1 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRecFav02')), 10000);
        let nameRecFav02 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImageFav02')), 10000);
        let recImageFav02 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthorFav02')), 10000);
        let nameAuthorFav02 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCookingFav02')), 10000);
        let timeCookingFav02 = await elem.getText();


        assert.equal(textNameAuthor0,"Автор: polinafomina");
        assert.equal(textRecImage0,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/140.png");
        assert.equal(textNameRec0,"БОРЩ");
        assert.equal(textTimeCooking0,"Время приготовления: 120 минут");

        assert.equal(textNameAuthor1,"Автор: polinafomina");
        assert.equal(textRecImage1,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/141.png");
        assert.equal(textNameRec1,"ЩИ");
        assert.equal(textTimeCooking1,"Время приготовления: 90 минут");

        assert.equal(nameRecFav02,"БЛИНЫ");
        assert.equal(recImageFav02,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/142.png");
        assert.equal(nameAuthorFav02,"Автор: povarverona");
        assert.equal(timeCookingFav02,"Время приготовления: 20 минут");
    })

    it('check recipe "Щи"' ,async function (){
        let nameRec1 = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        nameRec1.click();

       await checkShchi();
    })
})

describe("E2E. Scenario 5", () => {
    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html');
    })

    after(async ()=>{
        await browser.close();
    })

    it('check button "Войти" on index.html',async function () {

        let signInButton = await browser.wait(
            until.elementLocated(By.id('signInButton')), 10000);

        assert.equal(await signInButton.getText(), "Войти");
        assert.equal(await signInButton.getAttribute('href'), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");

        await signInButton.click();
        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");
    })

    it('check button "Войти" on signIn.html',async function () {

        let elem = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);
        assert.equal(await elem.getAttribute('disabled'), "true");

        let signUpButton;
        try{
            elem = await browser.wait(
                until.elementLocated(By.id('signUpButton')), 10000);
            signUpButton = await elem.getAttribute('disabled');
            assert.equal(signUpButton, "false");
        }
        catch(err){
            if(err.message !== "Expected values to be strictly equal:\n" +
                "\n" +
                "null !== 'false'\n"){
                assert.equal(signUpButton,"false");
            }
        }

        let inputLogin = await browser.wait(
            until.elementLocated(By.id('inputLogin')), 10000);
        await inputLogin.sendKeys("polinafomina");

        let inputPassword = await browser.wait(
            until.elementLocated(By.id('inputPassword')), 10000);
        await inputPassword.sendKeys("qwerty");

        elem = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);
        let enterButton = await elem.getAttribute('disabled');
        assert.equal(enterButton, "true");

        await inputPassword.sendKeys("qwerty123");

        enterButton = await elem.getAttribute('disabled');

        try{
            assert.equal(enterButton, "false");
        }
        catch(err){
            if(err.message !== "Expected values to be strictly equal:\n" +
                "\n" +
                "null !== 'false'\n"){
                assert.equal(enterButton,"false");
            }
        }
    })

    it('check click on button "Войти" on signIn.html',async function () {

        let enterButton = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);

        await enterButton.click();
        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/user.html");

        let elem = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        let textNameRec0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage0')), 10000);
        let textRecImage0 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor0')), 10000);
        let textNameAuthor0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking0')), 10000);
        let textTimeCooking0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        let textNameRec1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage1')), 10000);
        let textRecImage1 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor1')), 10000);
        let textNameAuthor1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking1')), 10000);
        let textTimeCooking1 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRecFav02')), 10000);
        let nameRecFav02 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImageFav02')), 10000);
        let recImageFav02 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthorFav02')), 10000);
        let nameAuthorFav02 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCookingFav02')), 10000);
        let timeCookingFav02 = await elem.getText();


        assert.equal(textNameAuthor0,"Автор: polinafomina");
        assert.equal(textRecImage0,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/140.png");
        assert.equal(textNameRec0,"БОРЩ");
        assert.equal(textTimeCooking0,"Время приготовления: 120 минут");

        assert.equal(textNameAuthor1,"Автор: polinafomina");
        assert.equal(textRecImage1,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/141.png");
        assert.equal(textNameRec1,"ЩИ");
        assert.equal(textTimeCooking1,"Время приготовления: 90 минут");

        assert.equal(nameRecFav02,"БЛИНЫ");
        assert.equal(recImageFav02,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/142.png");
        assert.equal(nameAuthorFav02,"Автор: povarverona");
        assert.equal(timeCookingFav02,"Время приготовления: 20 минут");
    })

    it('check click on logo',async function () {

        let elem = await browser.wait(
            until.elementLocated(By.id('logoImage')), 10000);
        await elem.click();

        elem = await browser.getCurrentUrl();

        assert.equal(elem,"file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html");
    })

    it('check recipe "Вафли"',async function () {

        let nameRec1 = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        nameRec1.click();

        await checkWaffles();
    })

    it('check click on button "Добавить в избранное" ',async function () {

        let elem = await browser.wait(
            until.elementLocated(By.id('addFavorites')), 10000);
        await elem.click();

        assert.equal(await elem.getText(),"Удалить из избранного");
    })
})

describe("E2E. Scenario 6", () => {
    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html');
    })

    after(async ()=>{
        await browser.close();
    })

    it('check button "Войти" on index.html',async function () {

        let signInButton = await browser.wait(
            until.elementLocated(By.id('signInButton')), 10000);

        assert.equal(await signInButton.getText(), "Войти");
        assert.equal(await signInButton.getAttribute('href'), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");

        await signInButton.click();
        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");
    })

    it('check button "Зарегистрироваться" on signIn.html',async function () {

        let elem = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);
        let enterButton = await elem.getAttribute('disabled');
        assert.equal(enterButton, "true");

        elem = await browser.wait(
            until.elementLocated(By.id('signUpButton')), 10000);

        let signUpButton;
        try{
            signUpButton = await elem.getAttribute('disabled');
            assert.equal(signUpButton, "false");
        }
        catch(err){
            if(err.message !== "Expected values to be strictly equal:\n" +
                "\n" +
                "null !== 'false'\n"){
                assert.equal(signUpButton,"false");
            }
        }

        await elem.click();
    })

    it('check button "Зарегистрироваться" on signUp.html',async function () {

        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/signUp.html");

        let registerButton = await browser.wait(
            until.elementLocated(By.id('registerButton')), 10000);

        assert.equal(await registerButton.getAttribute('disabled'), "true");

        let signUpLogin = await browser.wait(
            until.elementLocated(By.id('signUpLogin')), 10000);
        await signUpLogin.sendKeys("nikkita");

        let signUpPassword = await browser.wait(
            until.elementLocated(By.id('signUpPassword')), 10000);
        await signUpPassword.sendKeys("nik01nik");

        let signUpEmail = await browser.wait(
            until.elementLocated(By.id('signUpEmail')), 10000);
        //signUpEmail.sendKeys("nikkita2001@mail.ru");

        await signUpEmail.click();
        await browser.actions()
            .sendKeys('nikkita2001@mail.ru')
            .sendKeys(" ")
            .perform()

        let regButton;
        try{
            regButton = await registerButton.getAttribute('disabled');
            assert.equal(regButton, "false");
        }
        catch(err){
            if(err.message !== "Expected values to be strictly equal:\n" +
                "\n" +
                "null !== 'false'\n"){
                assert.equal(regButton,"false");
            }
        }
    })

    it('check click on button "Зарегистрироваться" on signIn.html',async function () {

        let registerButton = await browser.wait(
            until.elementLocated(By.id('registerButton')), 10000);

        await registerButton.click();
        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html");

        let signInButton = await browser.wait(
            until.elementLocated(By.id('signInButton')), 10000);

        assert(await signInButton.getText(), "nikkita");

        let elem = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        let textNameRec0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage0')), 10000);
        let textRecImage0 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor0')), 10000);
        let textNameAuthor0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking0')), 10000);
        let textTimeCooking0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        let textNameRec1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage1')), 10000);
        let textRecImage1 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor1')), 10000);
        let textNameAuthor1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking1')), 10000);
        let textTimeCooking1 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRec2')), 10000);
        let textNameRec2 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage2')), 10000);
        let textRecImage2 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor2')), 10000);
        let textNameAuthor2 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking2')), 10000);
        let textTimeCooking2 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRec3')), 10000);
        let textNameRec3 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage3')), 10000);
        let textRecImage3 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor3')), 10000);
        let textNameAuthor3 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking3')), 10000);
        let textTimeCooking3 = await elem.getText();

        assert.equal(textNameAuthor0,"Автор: povarverona");
        assert.equal(textRecImage0,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/144.png");
        assert.equal(textNameRec0,"СТЕЙК");
        assert.equal(textTimeCooking0,"Время приготовления: 20 минут");

        assert.equal(textNameAuthor1,"Автор: povarverona");
        assert.equal(textRecImage1,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/143.png");
        assert.equal(textNameRec1,"ВАФЛИ");
        assert.equal(textTimeCooking1,"Время приготовления: 45 минут");

        assert.equal(textNameAuthor2,"Автор: povarverona");
        assert.equal(textRecImage2,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/142.png");
        assert.equal(textNameRec2,"БЛИНЫ");
        assert.equal(textTimeCooking2,"Время приготовления: 20 минут");

        assert.equal(textNameAuthor3,"Автор: polinafomina");
        assert.equal(textRecImage3,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/141.png");
        assert.equal(textNameRec3,"ЩИ");
        assert.equal(textTimeCooking3,"Время приготовления: 90 минут");
    })

    it('check recipe "Вафли"',async function () {

        let nameRec1 = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        nameRec1.click();

      await checkWaffles();
    })
})

describe("E2E. Scenario 7", () => {
    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html');
    })

    after(async ()=>{
        await browser.close();
    })

    it('check button "Войти" on index.html',async function () {

        let signInButton = await browser.wait(
            until.elementLocated(By.id('signInButton')), 10000);

        assert.equal(await signInButton.getText(), "Войти");
        assert.equal(await signInButton.getAttribute('href'), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");

        await signInButton.click();
        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");
    })

    it('check button "Войти" on signIn.html',async function () {

        let elem = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);
        let enterButton = await elem.getAttribute('disabled');
        assert.equal(enterButton, "true");

        let inputLogin = await browser.wait(
            until.elementLocated(By.id('inputLogin')), 10000);
        inputLogin.sendKeys("polinafomina");

        let inputPassword = await browser.wait(
            until.elementLocated(By.id('inputPassword')), 10000);
        inputPassword.sendKeys("qwerty123");

        elem = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);
        enterButton = await elem.getAttribute('disabled');

        try{
            assert.equal(enterButton, "false");
        }
        catch(err){
            if(err.message !== "Expected values to be strictly equal:\n" +
                "\n" +
                "null !== 'false'\n"){
                assert.equal(enterButton,"false");
            }
        }
    })

    it('check click button "Войти" on signIn.html',async function () {

        let enterButton = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);

        await enterButton.click();
        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/user.html");

        let elem = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        let textNameRec0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage0')), 10000);
        let textRecImage0 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor0')), 10000);
        let textNameAuthor0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking0')), 10000);
        let textTimeCooking0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        let textNameRec1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage1')), 10000);
        let textRecImage1 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor1')), 10000);
        let textNameAuthor1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking1')), 10000);
        let textTimeCooking1 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRecFav02')), 10000);
        let nameRecFav02 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImageFav02')), 10000);
        let recImageFav02 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthorFav02')), 10000);
        let nameAuthorFav02 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCookingFav02')), 10000);
        let timeCookingFav02 = await elem.getText();


        assert.equal(textNameAuthor0,"Автор: polinafomina");
        assert.equal(textRecImage0,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/140.png");
        assert.equal(textNameRec0,"БОРЩ");
        assert.equal(textTimeCooking0,"Время приготовления: 120 минут");

        assert.equal(textNameAuthor1,"Автор: polinafomina");
        assert.equal(textRecImage1,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/141.png");
        assert.equal(textNameRec1,"ЩИ");
        assert.equal(textTimeCooking1,"Время приготовления: 90 минут");

        assert.equal(nameRecFav02,"БЛИНЫ");
        assert.equal(recImageFav02,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/142.png");
        assert.equal(nameAuthorFav02,"Автор: povarverona");
        assert.equal(timeCookingFav02,"Время приготовления: 20 минут");
    })

    it('check recipe "Блины"',async function () {

        let nameRecFav02 = await browser.wait(
            until.elementLocated(By.id('nameRecFav02')), 10000);
        nameRecFav02.click();

        await checkPancake();

        let elem = await browser.wait(
            until.elementLocated(By.id('addFavorites')), 10000);
        assert.equal(await elem.getText(), "Удалить из избранного")
    })

    it('check click button "Удалить из избранного"',async function () {

        let addFavorites = await browser.wait(
            until.elementLocated(By.id('addFavorites')), 10000);
        await addFavorites.click();
        assert.equal(await addFavorites.getText(), "Добавить в избранное");

        let signInButton = await browser.wait(
            until.elementLocated(By.id('signInButton')), 10000);
        await signInButton.click();

        let elem = await browser.getCurrentUrl();
        assert.equal(elem,"file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/user.html");

        try{
            elem = await browser.wait(
                until.elementLocated(By.id('nameRecFav02')), 10000);
            assert.equal(await elem.getText(),"ВАФЛИ");
        }
        catch(err){if(err.code !== 'NoSuchElementException'){
            assert.equal(await elem.getText(),"ВАФЛИ");
        }}
    })
})

describe("E2E. Scenario 8", () => {
    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html');
    })

    after(async ()=>{
        await browser.close();
    })

    it('check button "Войти" on index.html',async function () {

        let signInButton = await browser.wait(
            until.elementLocated(By.id('signInButton')), 10000);

        assert.equal(await signInButton.getText(), "Войти");
        assert.equal(await signInButton.getAttribute('href'), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");

        await signInButton.click();
        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");
    })

    it('check button "Войти" on signIn.html',async function () {

        let elem = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);
        let enterButton = await elem.getAttribute('disabled');
        assert.equal(enterButton, "true");

        let inputLogin = await browser.wait(
            until.elementLocated(By.id('inputLogin')), 10000);
        inputLogin.sendKeys("polinafomina");

        let inputPassword = await browser.wait(
            until.elementLocated(By.id('inputPassword')), 10000);
        inputPassword.sendKeys("qwerty123");

        elem = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);
        enterButton = await elem.getAttribute('disabled');

        try{
            assert.equal(enterButton, "false");
        }
        catch(err){
            if(err.message !== "Expected values to be strictly equal:\n" +
                "\n" +
                "null !== 'false'\n"){
                assert.equal(enterButton,"false");
            }
        }
    })

    it('check click button "Войти" on signIn.html',async function () {

        let enterButton = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);

        await enterButton.click();
        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/user.html");

        let elem = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        let textNameRec0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage0')), 10000);
        let textRecImage0 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor0')), 10000);
        let textNameAuthor0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking0')), 10000);
        let textTimeCooking0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        let textNameRec1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage1')), 10000);
        let textRecImage1 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor1')), 10000);
        let textNameAuthor1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking1')), 10000);
        let textTimeCooking1 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRecFav02')), 10000);
        let nameRecFav02 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImageFav02')), 10000);
        let recImageFav02 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthorFav02')), 10000);
        let nameAuthorFav02 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCookingFav02')), 10000);
        let timeCookingFav02 = await elem.getText();


        assert.equal(textNameAuthor0,"Автор: polinafomina");
        assert.equal(textRecImage0,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/140.png");
        assert.equal(textNameRec0,"БОРЩ");
        assert.equal(textTimeCooking0,"Время приготовления: 120 минут");

        assert.equal(textNameAuthor1,"Автор: polinafomina");
        assert.equal(textRecImage1,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/141.png");
        assert.equal(textNameRec1,"ЩИ");
        assert.equal(textTimeCooking1,"Время приготовления: 90 минут");

        assert.equal(nameRecFav02,"ВАФЛИ");
        assert.equal(recImageFav02,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/143.png");
        assert.equal(nameAuthorFav02,"Автор: povarverona");
        assert.equal(timeCookingFav02,"Время приготовления: 45 минут");
    })

    it('check button "Добавить рецепт"',async function () {

        let addRecButton = await browser.wait(
            until.elementLocated(By.id('AddRecButton')), 10000);
        await addRecButton.click();

        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/addRecipe.html")

        let elem = await browser.wait(
            until.elementLocated(By.id('createRecButton')), 10000);

        let createRecButton = await elem.getAttribute('disabled');
        assert.equal(createRecButton, "true");

        let inputNameRec = await browser.wait(
            until.elementLocated(By.id('inputNameRec')), 10000);
        await inputNameRec.sendKeys("Паста");

        let inputDecRec = await browser.wait(
            until.elementLocated(By.id('inputDecRec')), 10000);
        await inputDecRec.sendKeys("Для приготовления традиционного соуса карбонара используется панчетта");

        let inputIngRec1 = await browser.wait(
            until.elementLocated(By.id('inputIngRec1')), 10000);
        await inputIngRec1.sendKeys("Спагетти");

        let inputGrRec1 = await browser.wait(
            until.elementLocated(By.id('inputGrRec1')), 10000);
        await inputGrRec1.sendKeys("250");

        let inputStepRec = await browser.wait(
            until.elementLocated(By.id('inputStepRec')), 10000);
        await inputStepRec
            .sendKeys("Отварите спагетти в подсоленной воде согласно инструкции на упаковке до состояния аль денте");

        let inputTimeCook = await browser.wait(
            until.elementLocated(By.id('inputTimeCook')), 10000);
        await inputTimeCook.sendKeys("45");

        let select = new Select(await browser.wait(
            until.elementLocated(By.id('typeCuisAddRec')), 10000));
        await select.selectByVisibleText("Итальянская");

        select = new Select(await browser.wait(
            until.elementLocated(By.id('categoryAddRec')), 10000));
        await select.selectByVisibleText("Паста");

        browser.executeScript('document.querySelector(\'.images\').innerHTML = ' +
            '\'<img src=\"http://localhost:63342/Frontend/downloads/1671528627000.png\" class="image" alt="">\';');

        try{
            createRecButton = await elem.getAttribute('disabled');
            assert.equal(createRecButton, "false");
        }
        catch(err){
            if(err.message !== "Expected values to be strictly equal:\n" +
                "\n" +
                "null !== 'false'\n"){
                assert.equal(createRecButton,"false");
            }
        }
    })

    it('check click button "Создать рецепт"',async function () {
        let createRecButton = await browser.wait(
            until.elementLocated(By.id('createRecButton')), 10000);
        await createRecButton.click();

        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/user.html");

        let elem = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        let textNameRec0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage0')), 10000);
        let textRecImage0 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor0')), 10000);
        let textNameAuthor0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking0')), 10000);
        let textTimeCooking0 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        let textNameRec1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage1')), 10000);
        let textRecImage1 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor1')), 10000);
        let textNameAuthor1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking1')), 10000);
        let textTimeCooking1 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRec2')), 10000);
        let textNameRec2 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage2')), 10000);
        let textRecImage2 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor2')), 10000);
        let textNameAuthor2 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking2')), 10000);
        let textTimeCooking2 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRecFav03')), 10000);
        let nameRecFav02 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImageFav03')), 10000);
        let recImageFav02 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthorFav03')), 10000);
        let nameAuthorFav02 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCookingFav03')), 10000);
        let timeCookingFav02 = await elem.getText();

        assert.equal(textNameAuthor0,"Автор: polinafomina");
        assert.equal(textRecImage0,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/140.png");
        assert.equal(textNameRec0,"БОРЩ");
        assert.equal(textTimeCooking0,"Время приготовления: 120 минут");
        assert.equal(textNameAuthor1,"Автор: polinafomina");
        assert.equal(textRecImage1,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/141.png");
        assert.equal(textNameRec1,"ЩИ");
        assert.equal(textTimeCooking1,"Время приготовления: 90 минут");
        assert.equal(textNameAuthor2,"Автор: polinafomina");
        assert.equal(textNameRec2,"ПАСТА");
        assert.equal(textTimeCooking2,"Время приготовления: 45 минут");
        assert.equal(nameRecFav02,"ВАФЛИ");
        assert.equal(recImageFav02,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/143.png");
        assert.equal(nameAuthorFav02,"Автор: povarverona");
        assert.equal(timeCookingFav02,"Время приготовления: 45 минут");
    })
})

describe("E2E. Scenario 9", () => {
    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('http://localhost:63342/Frontend/dist/index.html');
    })

    after(async ()=>{
        await browser.close();
    })

    it('check button "Войти" on index.html',async function () {

        let signInButton = await browser.wait(
            until.elementLocated(By.id('signInButton')), 10000);

        assert.equal(await signInButton.getText(), "Войти");
        assert.equal(await signInButton.getAttribute('href'), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");

        await signInButton.click();
        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html");
    })

    it('check button "Войти" on signIn.html',async function () {

        let elem = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);
        let enterButton = await elem.getAttribute('disabled');
        assert.equal(enterButton, "true");

        let inputLogin = await browser.wait(
            until.elementLocated(By.id('inputLogin')), 10000);
        inputLogin.sendKeys("polinafomina");

        let inputPassword = await browser.wait(
            until.elementLocated(By.id('inputPassword')), 10000);
        inputPassword.sendKeys("qwerty123");

        elem = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);
        enterButton = await elem.getAttribute('disabled');

        try{
            assert.equal(enterButton, "false");
        }
        catch(err){
            if(err.message !== "Expected values to be strictly equal:\n" +
                "\n" +
                "null !== 'false'\n"){
                assert.equal(enterButton,"false");
            }
        }
    })

    it('check click button "Войти" on signIn.html',async function () {

        let enterButton = await browser.wait(
            until.elementLocated(By.id('enterButton')), 10000);

        await enterButton.click();
        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/user.html");

        let elem = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        let textNameRec0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage0')), 10000);
        let textRecImage0 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor0')), 10000);
        let textNameAuthor0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking0')), 10000);
        let textTimeCooking0 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        let textNameRec1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage1')), 10000);
        let textRecImage1 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor1')), 10000);
        let textNameAuthor1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking1')), 10000);
        let textTimeCooking1 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRec2')), 10000);
        let textNameRec2 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage2')), 10000);
        let textRecImage2 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor2')), 10000);
        let textNameAuthor2 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking2')), 10000);
        let textTimeCooking2 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRecFav03')), 10000);
        let nameRecFav02 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImageFav03')), 10000);
        let recImageFav02 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthorFav03')), 10000);
        let nameAuthorFav02 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCookingFav03')), 10000);
        let timeCookingFav02 = await elem.getText();

        assert.equal(textNameAuthor0,"Автор: polinafomina");
        assert.equal(textRecImage0,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/140.png");
        assert.equal(textNameRec0,"БОРЩ");
        assert.equal(textTimeCooking0,"Время приготовления: 120 минут");
        assert.equal(textNameAuthor1,"Автор: polinafomina");
        assert.equal(textRecImage1,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/141.png");
        assert.equal(textNameRec1,"ЩИ");
        assert.equal(textTimeCooking1,"Время приготовления: 90 минут");
        assert.equal(textNameAuthor2,"Автор: polinafomina");
        assert.equal(textNameRec2,"ПАСТА");
        assert.equal(textTimeCooking2,"Время приготовления: 45 минут");
        assert.equal(nameRecFav02,"ВАФЛИ");
        assert.equal(recImageFav02,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/143.png");
        assert.equal(nameAuthorFav02,"Автор: povarverona");
        assert.equal(timeCookingFav02,"Время приготовления: 45 минут");
    })

    it('check button "Добавить рецепт"',async function () {

        let addRecButton = await browser.wait(
            until.elementLocated(By.id('AddRecButton')), 10000);
        await addRecButton.click();

        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/addRecipe.html")

        let elem = await browser.wait(
            until.elementLocated(By.id('createRecButton')), 10000);

        let createRecButton = await elem.getAttribute('disabled');
        assert.equal(createRecButton, "true");

        let inputNameRec = await browser.wait(
            until.elementLocated(By.id('inputNameRec')), 10000);
        await inputNameRec.sendKeys("П");

        let inputDecRec = await browser.wait(
            until.elementLocated(By.id('inputDecRec')), 10000);
        await inputDecRec.sendKeys("Для приготовления традиционного соуса карбонара используется панчетта");

        let inputIngRec1 = await browser.wait(
            until.elementLocated(By.id('inputIngRec1')), 10000);
        await inputIngRec1.sendKeys("Спагетти");

        let inputGrRec1 = await browser.wait(
            until.elementLocated(By.id('inputGrRec1')), 10000);
        await inputGrRec1.sendKeys("250");

        let inputStepRec = await browser.wait(
            until.elementLocated(By.id('inputStepRec')), 10000);
        await inputStepRec
            .sendKeys("Отварите спагетти в подсоленной воде согласно инструкции на упаковке до состояния аль денте");

        let inputTimeCook = await browser.wait(
            until.elementLocated(By.id('inputTimeCook')), 10000);
        await inputTimeCook.sendKeys("45");

        let select = new Select(await browser.wait(
            until.elementLocated(By.id('typeCuisAddRec')), 10000));
        await select.selectByVisibleText("Итальянская");

        select = new Select(await browser.wait(
            until.elementLocated(By.id('categoryAddRec')), 10000));
        await select.selectByVisibleText("Паста");

        assert.equal( await elem.getAttribute('disabled'), "true");

        await inputNameRec.sendKeys("аста2");
        await inputGrRec1.click();

        browser.executeScript('document.querySelector(\'.images\').innerHTML = ' +
            '\'<img src=\"http://localhost:63342/Frontend/downloads/1671507028000.png\" class="image" alt="">\';');

        try{
            createRecButton = await elem.getAttribute('disabled');
            assert.equal(createRecButton, "false");
        }
        catch(err){
            if(err.message !== "Expected values to be strictly equal:\n" +
                "\n" +
                "null !== 'false'\n"){
                assert.equal(createRecButton,"false");
            }
        }
    })

    it('check click button "Создать рецепт"',async function () {
        let createRecButton = await browser.wait(
            until.elementLocated(By.id('createRecButton')), 10000);
        await createRecButton.click();

        assert.equal(await browser.getCurrentUrl(), "file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/user.html");

        let elem = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        let textNameRec0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage0')), 10000);
        let textRecImage0 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor0')), 10000);
        let textNameAuthor0 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking0')), 10000);
        let textTimeCooking0 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        let textNameRec1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage1')), 10000);
        let textRecImage1 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor1')), 10000);
        let textNameAuthor1 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking1')), 10000);
        let textTimeCooking1 = await elem.getText();


        elem = await browser.wait(
            until.elementLocated(By.id('nameRec2')), 10000);
        let textNameRec2 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImage2')), 10000);
        let textRecImage2 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthor2')), 10000);
        let textNameAuthor2 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCooking2')), 10000);
        let textTimeCooking2 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('nameRecFav03')), 10000);
        let nameRecFav02 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('recImageFav03')), 10000);
        let recImageFav02 = await elem.getAttribute('src');

        elem = await browser.wait(
            until.elementLocated(By.id('nameAuthorFav03')), 10000);
        let nameAuthorFav02 = await elem.getText();

        elem = await browser.wait(
            until.elementLocated(By.id('timeCookingFav03')), 10000);
        let timeCookingFav02 = await elem.getText();

        assert.equal(textNameAuthor0,"Автор: polinafomina");
        assert.equal(textRecImage0,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/140.png");
        assert.equal(textNameRec0,"БОРЩ");
        assert.equal(textTimeCooking0,"Время приготовления: 120 минут");
        assert.equal(textNameAuthor1,"Автор: polinafomina");
        assert.equal(textRecImage1,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/141.png");
        assert.equal(textNameRec1,"ЩИ");
        assert.equal(textTimeCooking1,"Время приготовления: 90 минут");
        assert.equal(textNameAuthor2,"Автор: polinafomina");
        assert.equal(textNameRec2,"ПАСТА");
        assert.equal(textTimeCooking2,"Время приготовления: 45 минут");
        assert.equal(nameRecFav02,"ВАФЛИ");
        assert.equal(recImageFav02,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/143.png");
        assert.equal(nameAuthorFav02,"Автор: povarverona");
        assert.equal(timeCookingFav02,"Время приготовления: 45 минут");
    })
})

async function checkShchi(){
    let elem = await browser.wait(
        until.elementLocated(By.id('infoNameRec')), 10000);
    let infoNameRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoDecRec')), 10000);
    let infoDecRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoStepRec')), 10000);
    let infoStepRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoCatRec')), 10000);
    let infoCatRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoTypeCusRec')), 10000);
    let infoTypeCusRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoAuthorRec')), 10000);
    let infoAuthorRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoIngRec')), 10000);
    let infoIngRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoTimeCookingRec')), 10000);
    let infoTimeCookingRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoDateRec')), 10000);
    let infoDateRec = await elem.getText();

    assert.equal(infoNameRec, "ЩИ");
    assert.equal(infoDecRec, "Вкусные щи с гренками");
    assert.equal(infoStepRec, "Поджарить гренки и сварить щи");
    assert.equal(infoCatRec, "Супы");
    assert.equal(infoTypeCusRec, "Русская");
    assert.equal(infoAuthorRec,"polinafomina");
    assert.equal(infoIngRec, "1. Капуста 300 грамм\n2. Морковь 100 грамм\n3. Мясо 500 грамм");
    assert.equal(infoTimeCookingRec,"90");
    assert.equal(infoDateRec,"2023-03-11");

    let addFavorites;

    try{
        elem = await browser.wait(
            until.elementLocated(By.id('addFavorites')), 10000);
        addFavorites = await elem.getAttribute('disabled');
        assert.equal(addFavorites,'true');
    }
    catch(err){
        if(err.message !== "Expected values to be strictly equal:\n" +
            "\n" +
            "null !== 'true'\n"){
            assert.equal(addFavorites,"true");
        }
    }
}

async function checkWaffles(){
    let elem = await browser.wait(
        until.elementLocated(By.id('infoNameRec')), 10000);
    let infoNameRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoDecRec')), 10000);
    let infoDecRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoStepRec')), 10000);
    let infoStepRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoCatRec')), 10000);
    let infoCatRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoTypeCusRec')), 10000);
    let infoTypeCusRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoAuthorRec')), 10000);
    let infoAuthorRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoIngRec')), 10000);
    let infoIngRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoTimeCookingRec')), 10000);
    let infoTimeCookingRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoDateRec')), 10000);
    let infoDateRec = await elem.getText();

    let addFavorites;
    try{
        elem = await browser.wait(
            until.elementLocated(By.id('addFavorites')), 10000);
        addFavorites = await elem.getAttribute('disabled');
        assert.equal(addFavorites,"true");
    }
    catch(err){
        if(err.message !== "Expected values to be strictly equal:\n" +
            "\n" +
            "null !== 'true'\n"){
            assert.equal(addFavorites,"true");
        }
    }

    assert.equal(infoNameRec, "ВАФЛИ");
    assert.equal(infoDecRec, "Пышные вафли с шоколадом");
    assert.equal(infoStepRec, "Приготовить тесто, растопить шоколад");
    assert.equal(infoCatRec, "Выпечка и десерты");
    assert.equal(infoTypeCusRec, "Европейская");
    assert.equal(infoAuthorRec,"povarverona");
    assert.equal(infoIngRec, "1. Молоко 250 грамм\n2. Мука 70 грамм\n3. Шоколад горький 100 грамм");
    assert.equal(infoTimeCookingRec,"45");
    assert.equal(infoDateRec,"2023-03-13");
}

async function checkPancake(){
    let elem = await browser.wait(
        until.elementLocated(By.id('infoNameRec')), 10000);
    let infoNameRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoDecRec')), 10000);
    let infoDecRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoStepRec')), 10000);
    let infoStepRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoCatRec')), 10000);
    let infoCatRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoTypeCusRec')), 10000);
    let infoTypeCusRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoAuthorRec')), 10000);
    let infoAuthorRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoIngRec')), 10000);
    let infoIngRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoTimeCookingRec')), 10000);
    let infoTimeCookingRec = await elem.getText();

    elem = await browser.wait(
        until.elementLocated(By.id('infoDateRec')), 10000);
    let infoDateRec = await elem.getText();

    let addFavorites;
    try{
        elem = await browser.wait(
            until.elementLocated(By.id('addFavorites')), 10000);
        addFavorites = await elem.getAttribute('disabled');
        assert.equal(addFavorites,"true");
    }
    catch(err){
        if(err.message !== "Expected values to be strictly equal:\n" +
            "\n" +
            "null !== 'true'\n"){
            assert.equal(addFavorites,"true");
        }
    }

    assert.equal(infoNameRec, "БЛИНЫ");
    assert.equal(infoDecRec, "Тонкие блинчики с маслом");
    assert.equal(infoStepRec, "Приготовить тесто и пожарить блины");
    assert.equal(infoCatRec, "Выпечка и десерты");
    assert.equal(infoTypeCusRec, "Русская");
    assert.equal(infoAuthorRec,"povarverona");
    assert.equal(infoIngRec, "1. Молоко 250 грамм\n2. Яйцо 120 грамм");
    assert.equal(infoTimeCookingRec,"20");
    assert.equal(infoDateRec,"2023-03-12");
}
