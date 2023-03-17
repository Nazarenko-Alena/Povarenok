const {By,Builder, until, Select} = require("selenium-webdriver");
const assert = require('assert').strict;
require("chromedriver");

//const jsdom = require('jsdom');
//const dom = new jsdom.JSDOM("");
//const $ = require('jquery')(dom.window);

let browser;

describe("Scenario 13 - Set new user", () => {
    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('http://localhost:63343/Frontend/dist/signUp.html');
    })

    after(async ()=>{
        await browser.close();
    })

    it('check setNewUser',async function () {

        let signUpLogin = await browser.wait(
            until.elementLocated(By.id('signUpLogin')), 10000);
        signUpLogin.sendKeys("nazarenko");

        let signUpPassword = await browser.wait(
            until.elementLocated(By.id('signUpPassword')), 10000);
        signUpPassword.sendKeys("123456789");

        let signUpEmail = await browser.wait(
            until.elementLocated(By.id('signUpEmail')), 10000);
        signUpEmail.sendKeys("nazarenko.av@edu.spbstu.ru");

        let registerButton = await browser.wait(
            until.elementLocated(By.id('registerButton')), 10000);
        registerButton.click();

        let signInButton = await browser.wait(
            until.elementLocated(By.id('signInButton')), 10000);

        assert(await signInButton.getText(), "nazarenko");
    })
})

describe("Scenario 14 - Recent recipe", () => {

    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('http://localhost:63343/Frontend/dist/index.html');
    })

    after(async ()=>{
        await browser.close();
    })


    it('check nameRecipe0',async function () {
        let element = await browser.wait(
          until.elementLocated(By.id('nameRec0')), 10000);
        let text = await element.getText();

        assert.equal(text,"СТЕЙК");
    });
    it('check recImage0',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('recImage0')), 10000);
        let text = await element.getAttribute('src');

        assert.equal(text,"http://localhost:63343/Frontend/downloads/144.png");
    });
    it('check nameAuthor0',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('nameAuthor0')), 10000);
        let text = await element.getText();

        assert.equal(text,"Автор: povarverona");
    });
    it('check timeCooking0',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('timeCooking0')), 10000);
        let text = await element.getText();

        assert.equal(text,"Время приготовления: 20 минут");
    });



    it('check nameRecipe1',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        let text = await element.getText();

        assert.equal(text,"ВАФЛИ");
    });
    it('check recImage1',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('recImage1')), 10000);
        let text = await element.getAttribute('src');

        assert.equal(text,"http://localhost:63343/Frontend/downloads/143.png");
    });
    it('check nameAuthor1',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('nameAuthor1')), 10000);
        let text = await element.getText();

        assert.equal(text,"Автор: povarverona");
    });
    it('check timeCooking1',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('timeCooking1')), 10000);
        let text = await element.getText();

        assert.equal(text,"Время приготовления: 45 минут");
    });



    it('check nameRecipe2',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('nameRec2')), 10000);
        let text = await element.getText();

        assert.equal(text,"БЛИНЫ");
    });
    it('check recImage2',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('recImage2')), 10000);
        let text = await element.getAttribute('src');

        assert.equal(text,"http://localhost:63343/Frontend/downloads/142.png");
    });
    it('check nameAuthor2',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('nameAuthor2')), 10000);
        let text = await element.getText();

        assert.equal(text,"Автор: povarverona");
    });
    it('check timeCooking2',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('timeCooking2')), 10000);
        let text = await element.getText();

        assert.equal(text,"Время приготовления: 20 минут");
    });



    it('check nameRecipe3',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('nameRec3')), 10000);
        let text = await element.getText();

        assert.equal(text,"ЩИ");
    });
    it('check recImage3',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('recImage3')), 10000);
        let text = await element.getAttribute('src');

        assert.equal(text,"http://localhost:63343/Frontend/downloads/141.png");
    });
    it('check nameAuthor3',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('nameAuthor3')), 10000);
        let text = await element.getText();

        assert.equal(text,"Автор: polinafomina");
    });
    it('check timeCooking3',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('timeCooking3')), 10000);
        let text = await element.getText();

        assert.equal(text,"Время приготовления: 90 минут");
    });

})

describe("Scenario 15 - Search result by keyword", () => {

    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('http://localhost:63343/Frontend/dist/index.html');
        let searchLine = await browser.wait(
            until.elementLocated(By.id('searchLine')), 10000);
        searchLine.sendKeys("Борщ");
        let findButton = await browser.wait(
            until.elementLocated(By.id('findButton')), 10000);
        findButton.click();
    })

    after(async ()=>{
        await browser.close();
    })

    it('check nameRecipe0',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        let text = await element.getText();

        assert.equal(text,"БОРЩ");
    });
    it('check recImage0',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('recImage0')), 10000);
        let text = await element.getAttribute('src');

        assert.equal(text,"http://localhost:63343/Frontend/downloads/140.png");
    });
    it('check nameAuthor0',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('nameAuthor0')), 10000);
        let text = await element.getText();

        assert.equal(text,"Автор: polinafomina");
    });

    it('check timeCooking0',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('timeCooking0')), 10000);
        let text = await element.getText();

        assert.equal(text,"Время приготовления: 120 минут");
    });

})

describe("Scenario 16 - Get recipe by name", () => {

    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('http://localhost:63343/Frontend/dist/index.html');
        let nameRec0 = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        nameRec0.click();
    })

    after(async ()=>{
        await browser.close();
    })

    it('check infoNameRec',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('infoNameRec')), 10000);
        let text = await element.getText();

        assert.equal(text,"СТЕЙК");
    });
    it('check infoDecRec',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('infoDecRec')), 10000);
        let text = await element.getText();

        assert.equal(text,"Стейк Нью-Йорк");
    });
    it('check infoStepRec',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('infoStepRec')), 10000);
        let text = await element.getText();

        assert.equal(text,"Посолить и поперчить мясо, пожарить на гриле");
    });
    it('check infoCatRec',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('infoCatRec')), 10000);
        let text = await element.getText();

        assert.equal(text,"Основные блюда");
    });
    it('check infoTypeCusRec',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('infoTypeCusRec')), 10000);
        let text = await element.getText();

        assert.equal(text,"Французская");
    });
    it('check infoAuthorRec',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('infoAuthorRec')), 10000);
        let text = await element.getText();

        assert.equal(text,"povarverona");
    });
    it('check infoIngRec',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('infoIngRec')), 10000);
        let text = await element.getText();

        assert.equal(text,"1. Мясо 320 грамм");
    });
    it('check infoTimeCookingRec',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('infoTimeCookingRec')), 10000);
        let text = await element.getText();

        assert.equal(text,"20");
    });
    it('check infoDateRec',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('infoDateRec')), 10000);
        let text = await element.getText();

        assert.equal(text,"2023-03-14");
    });
    it('check infoRecImage',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('infoRecImage')), 10000);
        let text = await element.getAttribute('src');

        assert.equal(text,"http://localhost:63343/Frontend/downloads/144.png");
    });
})

describe("Scenario 17 - Get cuisines from AddRecipe.html", () => {

    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('http://localhost:63343/Frontend/dist/addRecipe.html');
    })

    after(async ()=>{
        await browser.close();
    })

    it('check typeCuisAddRec content',async function () {
        let select = new Select(await browser.wait(
            until.elementLocated(By.id('typeCuisAddRec')), 10000));

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
    });
})

describe("Scenario 18 - Get categ from AddRecipe.html", () => {

    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();
        await browser.get('http://localhost:63343/Frontend/dist/addRecipe.html');
    })

    after(async ()=>{
        await browser.close();
    })

    it('check typeCuisAddRec content',async function () {
        let select = new Select(await browser.wait(
            until.elementLocated(By.id('categoryAddRec')), 10000));

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
    });
})
