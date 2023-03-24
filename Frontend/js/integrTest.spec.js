const {By,Builder, until, Select} = require("selenium-webdriver");
const assert = require('assert').strict;
require("chromedriver");
let chrome = require("selenium-webdriver/chrome");

let browser;

describe("Scenario 13 - Set new user", () => {

    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).setChromeOptions(new chrome.Options().headless()).build();
        await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signUp.html');
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
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).setChromeOptions(new chrome.Options().headless()).build();
        await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html');
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

        assert.equal(text,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/144.png");
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

        assert.equal(text,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/143.png");
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

        assert.equal(text,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/142.png");
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

        assert.equal(text,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/141.png");
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
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).setChromeOptions(new chrome.Options().headless()).build();
        await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html');
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
    // it('check recImage0',async function () {
    //     let element = await browser.wait(
    //         until.elementLocated(By.id('recImage0')), 10000);
    //     let text = await element.getAttribute('src');
    //
    //     assert.equal(text,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/140.png");
    // });
    // it('check nameAuthor0',async function () {
    //     let element = await browser.wait(
    //         until.elementLocated(By.id('nameAuthor0')), 10000);
    //     let text = await element.getText();
    //
    //     assert.equal(text,"Автор: polinafomina");
    // });
    //
    // it('check timeCooking0',async function () {
    //     let element = await browser.wait(
    //         until.elementLocated(By.id('timeCooking0')), 10000);
    //     let text = await element.getText();
    //
    //     assert.equal(text,"Время приготовления: 120 минут");
    // });

})

// describe("Scenario 16 - Get recipe by name", () => {
//
//     before(async ()=>{
//         browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).setChromeOptions(new chrome.Options().headless()).build();
//         await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html');
//         let nameRec0 = await browser.wait(
//             until.elementLocated(By.id('nameRec0')), 10000);
//         nameRec0.click();
//     })
//
//     after(async ()=>{
//         await browser.close();
//     })
//
//     it('check infoNameRec',async function () {
//         let element = await browser.wait(
//             until.elementLocated(By.id('infoNameRec')), 10000);
//         let text = await element.getText();
//
//         assert.equal(text,"СТЕЙК");
//     });
//     it('check infoDecRec',async function () {
//         let element = await browser.wait(
//             until.elementLocated(By.id('infoDecRec')), 10000);
//         let text = await element.getText();
//
//         assert.equal(text,"Стейк Нью-Йорк");
//     });
//     it('check infoStepRec',async function () {
//         let element = await browser.wait(
//             until.elementLocated(By.id('infoStepRec')), 10000);
//         let text = await element.getText();
//
//         assert.equal(text,"Посолить и поперчить мясо, пожарить на гриле");
//     });
//     it('check infoCatRec',async function () {
//         let element = await browser.wait(
//             until.elementLocated(By.id('infoCatRec')), 10000);
//         let text = await element.getText();
//
//         assert.equal(text,"Основные блюда");
//     });
//     it('check infoTypeCusRec',async function () {
//         let element = await browser.wait(
//             until.elementLocated(By.id('infoTypeCusRec')), 10000);
//         let text = await element.getText();
//
//         assert.equal(text,"Французская");
//     });
//     it('check infoAuthorRec',async function () {
//         let element = await browser.wait(
//             until.elementLocated(By.id('infoAuthorRec')), 10000);
//         let text = await element.getText();
//
//         assert.equal(text,"povarverona");
//     });
//     it('check infoIngRec',async function () {
//         let element = await browser.wait(
//             until.elementLocated(By.id('infoIngRec')), 10000);
//         let text = await element.getText();
//
//         assert.equal(text,"1. Мясо 320 грамм");
//     });
//     it('check infoTimeCookingRec',async function () {
//         let element = await browser.wait(
//             until.elementLocated(By.id('infoTimeCookingRec')), 10000);
//         let text = await element.getText();
//
//         assert.equal(text,"20");
//     });
//     it('check infoDateRec',async function () {
//         let element = await browser.wait(
//             until.elementLocated(By.id('infoDateRec')), 10000);
//         let text = await element.getText();
//
//         assert.equal(text,"2023-03-14");
//     });
//     it('check infoRecImage',async function () {
//         let element = await browser.wait(
//             until.elementLocated(By.id('infoRecImage')), 10000);
//         let text = await element.getAttribute('src');
//
//         assert.equal(text,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/144.png");
//     });
// })

// describe("Scenario 17 - Get cuisines from AddRecipe.html", () => {
//
//     before(async ()=>{
//         browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).setChromeOptions(new chrome.Options().headless()).build();
//         await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/addRecipe.html');
//     })
//
//     after(async ()=>{
//         await browser.close();
//     })
//
//     it('check typeCuisAddRec content',async function () {
//         let select = new Select(await browser.wait(
//             until.elementLocated(By.id('typeCuisAddRec')), 10000));
//
//         let optionList = await select.getOptions();
//
//         assert.equal(await optionList[1].getText(),"Американская");
//         assert.equal(await optionList[3].getText(),"Армянская");
//         assert.equal(await optionList[5].getText(),"Белорусская");
//         assert.equal(await optionList[7].getText(),"Британская");
//         assert.equal(await optionList[9].getText(),"Вьетнамская");
//         assert.equal(await optionList[11].getText(),"Греческая");
//         assert.equal(await optionList[13].getText(),"Грузинская");
//         assert.equal(await optionList[15].getText(),"Европейская");
//         assert.equal(await optionList[17].getText(),"Индийская");
//         assert.equal(await optionList[19].getText(),"Испанская");
//         assert.equal(await optionList[21].getText(),"Итальянская");
//         assert.equal(await optionList[23].getText(),"Китайская");
//         assert.equal(await optionList[25].getText(),"Корейская");
//         assert.equal(await optionList[27].getText(),"Мексиканская");
//         assert.equal(await optionList[29].getText(),"Паназиатская");
//         assert.equal(await optionList[31].getText(),"Русская");
//         assert.equal(await optionList[33].getText(),"Средиземноморская");
//         assert.equal(await optionList[35].getText(),"Тайская");
//         assert.equal(await optionList[37].getText(),"Узбекская");
//         assert.equal(await optionList[39].getText(),"Украинская");
//         assert.equal(await optionList[41].getText(),"Французская");
//         assert.equal(await optionList[43].getText(),"Японская");
//     });
// })
//
// describe("Scenario 18 - Get categ from AddRecipe.html", () => {
//
//     before(async ()=>{
//         browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).setChromeOptions(new chrome.Options().headless()).build();
//         await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/addRecipe.html');
//     })
//
//     after(async ()=>{
//         await browser.close();
//     })
//
//     it('check typeCuisAddRec content',async function () {
//         let select = new Select(await browser.wait(
//             until.elementLocated(By.id('categoryAddRec')), 10000));
//
//         let optionList = await select.getOptions();
//
//         assert.equal(await optionList[1].getText(),"Бульоны");
//         assert.equal(await optionList[3].getText(),"Выпечка и десерты");
//         assert.equal(await optionList[5].getText(),"Завтраки");
//         assert.equal(await optionList[7].getText(),"Закуски");
//         assert.equal(await optionList[9].getText(),"Напитки");
//         assert.equal(await optionList[11].getText(),"Основные блюда");
//         assert.equal(await optionList[13].getText(),"Паста");
//         assert.equal(await optionList[15].getText(),"Пиццы");
//         assert.equal(await optionList[17].getText(),"Салаты");
//         assert.equal(await optionList[19].getText(),"Соусы и маринады");
//         assert.equal(await optionList[21].getText(),"Супы");
//         assert.equal(await optionList[23].getText(),"Сэндвичи");
//     });
// })

describe("Scenario 19 - Search result by filters", () => {

    before(async ()=>{
        browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).setChromeOptions(new chrome.Options().headless()).build();
        await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/index.html');

        let filter1 = new Select(await browser.wait(
            until.elementLocated(By.id('categoryIndex')), 10000));

        await filter1.selectByVisibleText("Супы");

        let filter2 = new Select(await browser.wait(
            until.elementLocated(By.id('typeCuis')), 10000));

        await filter2.selectByVisibleText("Русская");

        let applyButton = await browser.wait(
            until.elementLocated(By.id('applyButton')), 10000);
        applyButton.click();
    })

    after(async ()=>{
        await browser.close();
    })

    it('check nameRec0',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('nameRec0')), 10000);
        let text = await element.getText();

        assert.equal(text,"ЩИ");
    });
    // it('check recImage0',async function () {
    //     let element = await browser.wait(
    //         until.elementLocated(By.id('recImage0')), 10000);
    //     let text = await element.getAttribute('src');
    //
    //     assert.equal(text,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/141.png");
    // });
    // it('check nameAuthor0',async function () {
    //     let element = await browser.wait(
    //         until.elementLocated(By.id('nameAuthor0')), 10000);
    //     let text = await element.getText();
    //
    //     assert.equal(text,"Автор: polinafomina");
    // });
    // it('check timeCooking0',async function () {
    //     let element = await browser.wait(
    //         until.elementLocated(By.id('timeCooking0')), 10000);
    //     let text = await element.getText();
    //
    //     assert.equal(text,"Время приготовления: 90 минут");
    // });

    it('check nameRec1',async function () {
        let element = await browser.wait(
            until.elementLocated(By.id('nameRec1')), 10000);
        let text = await element.getText();

        assert.equal(text,"БОРЩ");
    });

    // it('check recImage1',async function () {
    //     let element = await browser.wait(
    //         until.elementLocated(By.id('recImage1')), 10000);
    //     let text = await element.getAttribute('src');
    //
    //     assert.equal(text,"file:///home/runner/work/Povarenok/Povarenok/Frontend/downloads/140.png");
    // });
    // it('check nameAuthor1',async function () {
    //     let element = await browser.wait(
    //         until.elementLocated(By.id('nameAuthor1')), 10000);
    //     let text = await element.getText();
    //
    //     assert.equal(text,"Автор: polinafomina");
    // });
    // it('check timeCooking1',async function () {
    //     let element = await browser.wait(
    //         until.elementLocated(By.id('timeCooking1')), 10000);
    //     let text = await element.getText();
    //
    //     assert.equal(text,"Время приготовления: 120 минут");
    // });
})

// describe("Scenario 20 - Add recipe in favorites", () => {
//
//     before(async ()=>{
//         browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).setChromeOptions(new chrome.Options().headless()).build();
//         await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html');
//         await browser.manage().deleteAllCookies();
//     })
//
//     after(async ()=>{
//         await browser.close();
//     })
//
//     it('authorization',async function () {
//         let signInLogin = await browser.wait(
//             until.elementLocated(By.id('inputLogin')), 10000);
//         signInLogin.sendKeys("polinafomina");
//
//         let signInPassword = await browser.wait(
//             until.elementLocated(By.id('inputPassword')), 10000);
//         signInPassword.sendKeys("qwerty123");
//
//         let enterButton = await browser.wait(
//             until.elementLocated(By.id('enterButton')), 10000);
//
//         await enterButton.click();
//
//         let nameRec1 = await browser.wait(
//             until.elementLocated(By.id('nameRec1')), 10000);
//         nameRec1.click();
//     });
//     it('check added recipe',async function () {
//         let addFav = await browser.wait(
//             until.elementLocated(By.id('addFavorites')), 10000);
//         addFav.click();
//
//         assert(await addFav.getText(),"Удалить из избранного");
//
//         let signInButton = await browser.wait(
//             until.elementLocated(By.id('signInButton')), 10000);
//         signInButton.click()
//
//
//         let element = await browser.wait(
//             until.elementLocated(By.id('nameRecFav12')), 10000);
//         let text = await element.getText();
//
//
//         assert.equal(text,"ЩИ");
//     });
// })
//
// describe("Scenario 21 - Delete recipe from favorites", () => {
//
//     before(async ()=>{
//         browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).setChromeOptions(new chrome.Options().headless()).build();
//         await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html');
//     })
//
//     after(async ()=>{
//         await browser.close();
//     })
//
//     it('authorization',async function () {
//         let signInLogin = await browser.wait(
//             until.elementLocated(By.id('inputLogin')), 10000);
//         signInLogin.sendKeys("polinafomina");
//
//         let signInPassword = await browser.wait(
//             until.elementLocated(By.id('inputPassword')), 10000);
//         signInPassword.sendKeys("qwerty123");
//
//         let enterButton = await browser.wait(
//             until.elementLocated(By.id('enterButton')), 10000);
//
//         await enterButton.click();
//
//         let nameRec1 = await browser.wait(
//             until.elementLocated(By.id('nameRecFav12')), 10000);
//         nameRec1.click();
//     });
//     it('check correct deletion',async function () {
//         let addFav = await browser.wait(
//             until.elementLocated(By.id('addFavorites')), 10000);
//         addFav.click();
//
//         assert(await addFav.getText(),"Добавить в избранное");
//
//         let signInButton = await browser.wait(
//             until.elementLocated(By.id('signInButton')), 10000);
//         signInButton.click()
//
//         let element = await browser.wait(
//             until.elementLocated(By.id('nameRecFav02')), 10000);
//         let text = await element.getText();
//
//         assert.equal(text,"БЛИНЫ");
//     });
// })
//
//
// describe("Scenario 22 - Add recipe", () => {
//
//     before(async ()=>{
//         browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).setChromeOptions(new chrome.Options().headless()).build();
//         await browser.get('file:///home/runner/work/Povarenok/Povarenok/Frontend/dist/signIn.html');
//     })
//
//     after(async ()=>{
//         await browser.close();
//     })
//
//     it('authorization',async function () {
//         let signInLogin = await browser.wait(
//             until.elementLocated(By.id('inputLogin')), 10000);
//         signInLogin.sendKeys("polinafomina");
//
//         let signInPassword = await browser.wait(
//             until.elementLocated(By.id('inputPassword')), 10000);
//         signInPassword.sendKeys("qwerty123");
//
//         let enterButton = await browser.wait(
//             until.elementLocated(By.id('enterButton')), 10000);
//
//         await enterButton.click();
//
//         let addRecButton = await browser.wait(
//             until.elementLocated(By.id('AddRecButton')), 10000);
//         addRecButton.click();
//     });
//     it('Add new recipe',async function () {
//         let nameRec = await browser.wait(
//             until.elementLocated(By.id('inputNameRec')), 10000);
//         nameRec.sendKeys("Курица");
//
//         let decRec = await browser.wait(
//             until.elementLocated(By.id('inputDecRec')), 10000);
//         decRec.sendKeys("Нежная курочка в кисло слакдом соусе");
//
//         let ingRec = await browser.wait(
//             until.elementLocated(By.id('inputIngRec1')), 10000);
//         ingRec.sendKeys("Курица охлажденная");
//
//         let grRec1 = await browser.wait(
//             until.elementLocated(By.id('inputGrRec1')), 10000);
//         grRec1.sendKeys("20");
//
//         let stepRec = await browser.wait(
//             until.elementLocated(By.id('inputStepRec')), 10000);
//         stepRec.sendKeys("Разделать и поставить в духовку на 180 градусов");
//
//
//         let filter1 = new Select(await browser.wait(
//             until.elementLocated(By.id('categoryAddRec')), 10000));
//         await filter1.selectByVisibleText("Основные блюда")
//
//
//         let filter2 = new Select(await browser.wait(
//             until.elementLocated(By.id('typeCuisAddRec')), 10000));
//         await filter2.selectByVisibleText("Русская");
//
//         let timeCook = await browser.wait(
//             until.elementLocated(By.id('inputTimeCook')), 10000);
//         timeCook.sendKeys("80");
//
//         await browser.executeScript('document.getElementById("addImg").remove()');
//         await browser.executeScript('document.getElementById("addImgRecText").remove()');
//         await browser.executeScript('document.querySelector(\'.images\').innerHTML = ' +
//             '\'<img src=\"http://localhost:63343/Frontend/downloads/1671532501000.png\" class="image" alt="">\';');
//
//         let elem = await browser.wait(
//             until.elementLocated(By.id('createRecButton')), 10000);
//         browser.executeScript('arguments[0].removeAttribute("disabled");', elem);
//         elem.click();
//
//     });
//     it('Check new added recipee',async function () {
//         browser.navigate().refresh();
//         let nameRec2 = await browser.wait(
//             until.elementLocated(By.id('nameRec2')), 10000);
//         let text = await nameRec2.getText();
//
//         assert.equal(text,"КУРИЦА");
//     });
// })
