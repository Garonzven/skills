var utils = require('./utils')
var config = require('../config')
var isProduction = process.env.NODE_ENV === 'production'
module.exports = {
	loaders: utils.cssLoaders({
		sourceMap: isProduction ? config.build.productionSourceMap : config.dev.cssSourceMap
		, extract: isProduction
	})
	, module: {
		loaders: [
			{
				test: /\.jpg$/
				, loader: "file-loader"
			}
			, {
				test: /\.png$/
				, loader: "url-loader?mimetype=image/png"
			}
	]
	}
	, output: {
		publicPath: "http://cdn.example.com/[hash]/"
	}
}