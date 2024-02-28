const APIDomain = 'http://127.0.0.1:8017'

const tabs = $('#tabs .nav-link')

const tabContents = $('#tabContent .tab-pane')

const defaultTabIndex = 0

// logout
$('#logoutBtn').click(function () {
    const APIUrl = `/user/logout`
    const params = {
        data: {}
    }
    axios.get(APIUrl, params)
        .then(function (response) {
            console.log(response.data);
            // change to login page
            location.href = `${APIDomain}/user/login.html`
        })
        .catch(function (error) {
            console.error(error);
        });
})

// find current active tab number
function getActiveTabIndex() {
    for (k = 0; k < tabs.length; k++) {
        if ($(tabs[k]).hasClass('active')) {
            return k
        }
    }
    return 0
}

// search
$('#btnPost').click(function () {
    var sendData = {"userId": "", "content": ""};
    sendData.userId =  sessionStorage.getItem("userId");
    sendData.content = $('#postContent').val();

    var jsonData = JSON.stringify(sendData);

    const APIUrl = `/blog/post`
    axios({
        method: 'post',
        url: APIUrl,
        data: jsonData,
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(response => {
        console.log(response);
        fetchBlogList()
    }).catch(err => {
        console.log(err);
    })
})

// tab click event
for (i = 0; i < tabs.length; i++) {
    const tabIndex = i
    const currentTab = $(tabs[tabIndex]);
    const currentTabContent = $(tabContents[tabIndex]);

    $(tabs[tabIndex]).click(function (e) {
        for (k = 0; k < tabContents.length; k++) {
            $(tabContents[k]).removeClass('active');
            $(tabContents[k]).removeClass('show');
        }
        for (j = 0; j < tabContents.length; j++) {
            $(tabs[j]).removeClass('active');
        }

        currentTabContent.addClass('active');
        currentTabContent.addClass('show');
        currentTab.addClass('active');

        // add list
        fetchList(tabs[tabIndex].name)
    })
}

// list
function fetchList() {

    var userId =  sessionStorage.getItem("userId");


    let followingAPIUrl = `${APIDomain}/user/relation/followee/list?userId=` + userId

    let followersAPIUrl = `${APIDomain}/user/relation/follower/list?userId=`+ userId

    axios({
        method: 'get',
        url: followingAPIUrl,
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(response => {
        console.log(response);
        $(`#number-following`).html(response.data.data?.length)
    }).catch(err => {
        console.log(err);
    })

    axios({
        method: 'get',
        url: followersAPIUrl,
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(response => {
        console.log(response);
        // 更新左侧圆圈数字
        $(`#number-followers`).html(response.data.data?.length)
    }).catch(err => {
        console.log(err);
    })
}

// 查询list
function fetchBlogList(type) {
    fetchList('following')
    let APIUrl = ''
    APIUrl = `/blog/list`

    var sendData = {"userId": "", "type": "", "page": 1, "content": 10};
    sendData.type = "PUBLIC";

    var jsonData = JSON.stringify(sendData);

    const activeTabIndex = getActiveTabIndex()
    const tabContent = $(tabContents[activeTabIndex])
    // 先清空内容区
    tabContent.html('')
    axios({
        method: 'post',
        url: APIUrl,
        data: jsonData,
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(response => {
        console.log(response);
        let html = []
        let errorMsg = ''
        if (response.data.code === 10) {
            html.push(`
            <table class="table">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">userId</th>
                <th scope="col">nickName</th>
                <th scope="col">content</th>
                <th scope="col">action</th>
              </tr>
            </thead>
            <tbody>
          `)

            const list = response.data.data || []

            for (k = 0; k < list.length; k++) {
                const {userId, nickName, content} = list[k]
                html.push(`
              <tr>
                <th scope="row">${k + 1}</th>
                <td>${userId}</td>
                <td>${nickName}</td>
                <td>${content}</td>
                <td>
                <button id="follow-btn" class="btn btn-primary" value="${userId}" onclick="follow()">follow</button>
                <button id="unfollow-btn" class="btn btn-primary" value="${userId}" onclick="unfollow()">unfollow</button>
              </tr>
            `)
            }

            html.push(`
            </tbody>
            </table>
          `)
        } else {
            errorMsg = `action failed, code: ${response.data.code}, message: ${response.data.message}`
        }
        // 渲染数据
        tabContent.html(errorMsg || html.join(''))
    }).catch(err => {
        console.log(err);
        alert(err.data.message)
    })
}

function follow(event) {

    const button = this.v;
    const index = parseInt(button.getAttribute('data-index'), 10);

    const cell = button.parentNode;
    // 再通过parentNode找到<tr>行
    const row = cell.parentNode;

    var sendData = {"userId": 2, "followeeId": 3};
    var followeeId = row.gegetElementsByTagName('td')[0].textContent;

    sendData.userId = sessionStorage.getItem("userId");
    sendData.followeeId = followeeId;
    var jsonData = JSON.stringify(sendData);
    let followAPIUrl = `${APIDomain}/user/relation/follow`

    axios({
        method: 'post',
        url: followAPIUrl,
        data: jsonData,
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(response => {
        console.log(response);
        alert(response.data.message)
    }).catch(err => {
        console.log(err);
        alert(err.data.message)
    })
}

function unfollow() {
    var sendData = {"userId": 2, "followerId": 3};
    var followerId = document.getElementsByTagName('td')[0].textContent;

    sendData.userId = sessionStorage.getItem("userId");
    sendData.followerId = followerId;

    var jsonData = JSON.stringify(sendData);

    let followAPIUrl = `${APIDomain}/user/relation/unfollow`

    axios({
        method: 'post',
        url: followAPIUrl,
        data: jsonData,
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(response => {
        console.log(response);
        alert(response.data.message)
    }).catch(err => {
        console.log(err);
        alert(err.data.message)
    })
}

fetchBlogList()