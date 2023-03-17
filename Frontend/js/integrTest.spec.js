const {By,Key,Builder, until} = require("selenium-webdriver");
const assert = require('assert').strict;
require("chromedriver");

const jsdom = require('jsdom');
const dom = new jsdom.JSDOM("");
const $ = require('jquery')(dom.window);

describe("Scenario 14", () => {
    let browser = new Builder().usingServer().withCapabilities({'browserName': 'chrome' }).build();

    afterEach(async ()=>{
       await browser.close();
    })

    it('check function getRecentRec return value',async function () {
        await browser.get('http://localhost:63343/function.js/dist/index.html');

        let element = await browser.findElement(By.id('nameRec0')).getText();

        assert.equal(element,"СТЕЙК");
        console.log(element);
    });
})