const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

let glob = require("glob");
let entry = __dirname + "./js/app.js";
let outputPath = __dirname + "/dist/";
if (process.env.TESTBUILD) {
    entry = glob.sync(__dirname + "./js/app.test.js");
    outputPath = __dirname + "/test-dist/";
}

const nodeExternals = require('webpack-node-externals');

module.exports = {
    entry: './js/app.js',
    module: {
        rules: [
            { test: /\.svg$/, use: 'svg-inline-loader' },
            {
                test: /\.m?js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: [
                            ['@babel/preset-env', { targets: "defaults" }]
                        ]
                    }
                    }},
            { test: /\.html$/i, loader: "html-loader"},
            {
                test: /\.css$/i,
                use: [MiniCssExtractPlugin.loader, "css-loader"],
            },
        ]
    },
    output: {
        path: path.resolve(__dirname, 'dist'),
        clean: true,
        filename: 'index_bundle.js',
        devtoolModuleFilenameTemplate: '[absolute-resource-path]',
        devtoolFallbackModuleFilenameTemplate: '[absolute-resource-path]?[hash]'
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: "./html/index.html"
        }),
        new HtmlWebpackPlugin({ template: "./html/searchRes.html",
            filename: "searchRes.html"
        }),
        new HtmlWebpackPlugin({ template: "./html/addRecipe.html",
            filename: "addRecipe.html"
        }),
        new HtmlWebpackPlugin({ template: "./html/recipe.html",
            filename: "recipe.html"
        }),
        new HtmlWebpackPlugin({ template: "./html/signIn.html",
            filename: "signIn.html"
        }),
        new HtmlWebpackPlugin({ template: "./html/signUp.html",
            filename: "signUp.html"
        }),
        new HtmlWebpackPlugin({ template: "./html/user.html",
            filename: "user.html"
        }),
        new MiniCssExtractPlugin(
            {
                filename: 'index.css',
            }
        )
    ]
}