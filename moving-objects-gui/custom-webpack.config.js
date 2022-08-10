const webpack = require('webpack');

module.exports = {
    plugins: [
        new webpack.DefinePlugin({
            $ENV: {
                ENVIRONMENT: JSON.stringify(process.env.ENVIRONMENT),
                ISSUER: JSON.stringify(process.env.ISSUER),
                CLIENT_ID: JSON.stringify(process.env.CLIENT_ID),
                CLIENT_SECRET: JSON.stringify(process.env.CLIENT_SECRET)
            }
        })
    ]
};
