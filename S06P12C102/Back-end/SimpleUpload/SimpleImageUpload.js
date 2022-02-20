const express = require("express");
const bodyParser = require("body-parser");
const fileUpload = require("express-fileupload");
const cors = require("cors");
const morgan = require("morgan");
const _ = require("lodash");
const fs = require("fs");

const mkdir = (dir) => {
    if (!fs.existsSync(dir)) {
        fs.mkdirSync(dir);
    }
}

const app = express();

app.use(fileUpload({
    createParentPath: true
}));

app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(morgan("dev"));

app.post("/upload", async (req, res) => {
    try {
        if (!req.files) {
            res.send({
                status: false,
                message: "upload fail"
            });
        } else {
            let file = req.files.uploadFile;
            let parentPath = req.body.parentPath;

            console.log(parentPath);
            if (!parentPath)
                file.mv(`/home/ubuntu/images/${file.name}`);
            else {
                let dir = `/home/ubuntu/images/${parentPath}`;
                mkdir(dir);
                file.mv(dir + `/${file.name}`);
            }
            res.send({
                status: true,
                message: "upload success",
                data: {
                    name: file.name,
                    minetype: file.minetype,
                    size: file.size
                }
            });
        }
    } catch (err) {
        res.status(500).send(err);
    }
});

const port = 3000;

app.listen(port, () => {
    console.log(`serving on port : ${port}`);
});