
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
            // location to login
            location.href = `/user/login.html`
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
$('#btnSearch').click(function () {
    const keyWords = $('#searchKeywords').val() || ''
    const APIUrl = `/search/searchUser?searchStr=${keyWords}`
    const params = {}

    const activeTabIndex = getActiveTabIndex()
    const tabContent = $(tabContents[activeTabIndex])
    // clean
    tabContent.html('')

    axios.get(APIUrl, params)
        // return success
        .then(function (response) {
            console.info(response);

            let html = []
            let errorMsg = ''
            if (response.data.code === 1) {
                html.push(`
            <table class="table">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">name</th>
                <th scope="col">email</th>
              </tr>
            </thead>
            <tbody>
          `)

                const list = typeof response.data.data === 'array' ? response.data.data : [response.data.data]

                for (k = 0; k < list.length; k++) {
                    const {realName, email} = list[k]
                    html.push(`
              <tr>
                <th scope="row">${k + 1}</th>
                <td>${realName}</td>
                <td>${email}</td>
                <td>
              </tr>
            `)
                }

                html.push(`
            </tbody>
            </table>
          `)
            } else {
                errorMsg = `Action failed, code: ${response.code}, message: ${response.message}`
            }

            tabContent.html(errorMsg || html.join(''))
        })
        .catch(function (error) {
            console.error(error);
        });
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

        // load list data
        fetchList(tabs[tabIndex].name)
    })
}

// list
function fetchList(type) {
    //load current Name
    searchCurrentUser4FollowPage()
    let APIUrl = ''
    const params = {}
    var userId = sessionStorage.getItem("userId");
    if (type === 'following') {
        APIUrl = `/user/relation/follower/list?userId=` + userId
    }
    if (type === 'followers') {
        APIUrl = `/user/relation/followee/list?userId=` + userId
    }

    const activeTabIndex = getActiveTabIndex()
    const tabContent = $(tabContents[activeTabIndex])
    // clear
    tabContent.html('')

    axios.get(APIUrl, params)
        .then(function (response) {
            console.log(response);
            let html = []
            let errorMsg = ''
            if (response.data.code === 1) {
                html.push(`
            <table class="table">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">name</th>
                <th scope="col">email</th>
                <th scope="col">action</th>
              </tr>
            </thead>
            <tbody>
          `)

                const list = response.data.data || []

                for (k = 0; k < list.length; k++) {
                    const {realName, userId, email} = list[k]
                    html.push(`
              <tr>
                <th scope="row">${k + 1}</th>
                <td>${realName}</td>
                <td>${email}</td>
                <td><button id="unfollow-btn" class="btn btn-primary" onclick="unfollow4FollowPage(${userId})">unfollow</button></td>
              </tr>
            `)
                }

                html.push(`
            </tbody>
            </table>
          `)
            } else {
                errorMsg = `Action failed, code: ${response.code}, message: ${response.message}`
            }
            tabContent.html(errorMsg || html.join(''))
            $(`#number-${type}`).html(errorMsg || response.data.data?.length)
        })
        .catch(function (error) {
            console.error(error);
        });
}

function unfollow4FollowPage(userId) {
    var sendData = {"userId": 2, "followerId": 3};
    sendData.userId = sessionStorage.getItem("userId");
    sendData.followerId = userId;

    var jsonData = JSON.stringify(sendData);

    let followAPIUrl = `/user/relation/unfollow`

    axios({
        method: 'post',
        url: followAPIUrl,
        data: jsonData,
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(response => {
        console.log(response);
        alert(response.data.message);
        location.reload();

    }).catch(err => {
        console.log(err);
        alert(err.data.message)
    })
}

function searchCurrentUser4FollowPage() {
    var userId = sessionStorage.getItem("userId");
    const APIUrl = `/search/searchUserById?userId=` + userId
    const params = {}


    axios.get(APIUrl, params)
        // return success
        .then(function (response) {
            console.info(response);

            var h5Element = document.querySelector(".left-menu-title");
            // 替换h5标签的内容
            h5Element.innerText = response.data.data.realName;

        })
        .catch(function (error) {
            console.error(error);
        });
}

fetchList(tabs[defaultTabIndex].name)