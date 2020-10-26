system = require('system')   //传递一些需要的参数给js文件

address = system.args[1];//获得命令行第二个参数 ，也就是指定要加载的页面地址，接下来会用到

var page = require('webpage').create();

var url = address;


page.open(url, function (status) {

    if (status !== 'success') {

        console.log('Unable to post!');
    } else {

        var encodings = ["euc-jp", "sjis", "utf8", "System"];//这一步是用来测试输出的编码格式，选择合适的编码格式很重要，不然你抓取下来的页面会乱码o(╯□╰)o，给出的几个编码格式是官网上的例子，根据具体需要自己去调整。

        for (var i = 3; i < encodings.length; i++) {//我这里只要一种编码就OK啦

            phantom.outputEncoding = encodings[i];

            console.log(phantom.outputEncoding+page.content);//最后返回webkit加载之后的页面内容
        }

    }
    phantom.exit();
});