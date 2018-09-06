import path from 'path';

const fileNames = ['index'];

const src = path.resolve(__dirname, 'src/main/resources/static/js');
const dist = path.resolve(__dirname, 'src/main/resources/static/dist');

const generateConfig = (name) => {
    return {
        mode: 'development',
        entry: `${src}/${name}.js`,

        output: {
            filename: `${name}.bundle.js`,
            path: dist,
        },
        resolve: {
            extensions: ['.js', '.json'],
        },
        module: {
            rules: [
                {
                    test: /\.(js)$/,
                    exclude: /node_modules/,
                    loader: 'babel-loader',
                },
            ],
        },
        plugins: [],
    }
};

const configList = fileNames.map(fileName => generateConfig(fileName));

export default configList;