  const APIDomain = 'http://10.35.4.80:8017'
  // tab数组
  const tabs = $('#tabs .nav-link')
  // tab内容区数组
  const tabContents = $('#tabContent .tab-pane')
  // 默认激活的tab序号
  const defaultTabIndex = 0

  // 退出
  $('#logoutBtn').click(function() {
    const APIUrl = `${APIDomain}/user/logout`
    const params = {
      data: {}
    }
    axios.get(APIUrl, params)
      .then(function(response) {
        console.log(response.data);
        // 跳转到登录页
        location.href = `${APIDomain}/user/login.html`
      })
      .catch(function(error) {
        console.error(error);
      });
  })

  // 找到当前active的tab序号
  function getActiveTabIndex() {
    for (k = 0; k < tabs.length; k++) {
      if ($(tabs[k]).hasClass('active')) {
        return k
      }
    }
    return 0
  }

  // search
  $('#btnSearch').click(function() {
    const keyWords = $('#searchKeywords').val() || ''
    const APIUrl = `${APIDomain}/search/searchUser?searchStr=${keyWords}`
    const params = {}

    const activeTabIndex = getActiveTabIndex()
    const tabContent = $(tabContents[activeTabIndex])
    // 先清空内容区
    tabContent.html('')

    axios.get(APIUrl, params)
      // 接口成功返回的处理
      .then(function(response) {
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

          // 兼容data可能是数组的情况
          const list = typeof response.data.data === 'array' ? response.data.data : [response.data.data]

          for (k = 0; k < list.length; k++) {
            const { nickName, email } = list[k]
            html.push(`
              <tr>
                <th scope="row">${k + 1}</th>
                <td>${nickName}</td>
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
          errorMsg = `操作失败, code: ${response.code}, message: ${response.message}`
        }
        // 渲染数据
        tabContent.html(errorMsg || html.join(''))
      })
      .catch(function(error) {
        console.error(error);
      });
  })

  // tab点击事件
  for (i = 0; i < tabs.length; i++) {
    const tabIndex = i
    const currentTab = $(tabs[tabIndex]);
    const currentTabContent = $(tabContents[tabIndex]);

    $(tabs[tabIndex]).click(function(e) {
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

      // 加载list数据
      fetchList(tabs[tabIndex].name)
    })
  }

  // 查询list
  function fetchList(type) {
    let APIUrl = ''
    const params = {}

    if (type === 'following') {
      APIUrl = `${APIDomain}/user/relation/followee/list?userId=2`
    }
    if (type === 'followers') {
      APIUrl = `${APIDomain}/user/relation/follower/list?userId=2`
    }

    const activeTabIndex = getActiveTabIndex()
    const tabContent = $(tabContents[activeTabIndex])
    // 先清空内容区
    tabContent.html('')

    axios.get(APIUrl, params)
      .then(function(response) {
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
              </tr>
            </thead>
            <tbody>
          `)

          const list = response.data.data || []

          for (k = 0; k < list.length; k++) {
            const { nickName, email } = list[k]
            html.push(`
              <tr>
                <th scope="row">${k + 1}</th>
                <td>${nickName}</td>
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
          errorMsg = `操作失败, code: ${response.code}, message: ${response.message}`
        }
        // 渲染数据
        tabContent.html(errorMsg || html.join(''))
        // 更新左侧圆圈数字
        $(`#number-${type}`).html(errorMsg || response.data.data?.length)
      })
      .catch(function(error) {
        console.error(error);
      });
  }
  // 默认加载第一个tab的数据
  fetchList(tabs[defaultTabIndex].name)