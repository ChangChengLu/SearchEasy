<template>
  <div class="index-page">
    <a-input-search
      v-model:value="searchText"
      placeholder="请输⼊搜索关键词"
      enter-button="搜索"
      size="large"
      @search="onSearch"
    />
    <my-divider />
    <a-tabs v-model:activeKey="activeKey" @change="onTabChange">
      <a-tab-pane key="post" tab="文章">
        <PostList :post-list="postList" />
      </a-tab-pane>
      <a-tab-pane key="picture" tab="图片">
        <PictureList :picture-list="pictureList" />
      </a-tab-pane>
      <a-tab-pane key="user" tab="用户">
        <UserList :user-list="userList" />
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script setup lang="ts">
import { ref, watchEffect } from "vue";
import PostList from "@/components/PostList.vue";
import MyDivider from "@/components/MyDivider.vue";
import { useRoute, useRouter } from "vue-router";
import PictureList from "@/components/PictureList.vue";
import UserList from "@/components/UserList.vue";
import myAxios from "../../plugins/MyAxios";

const router = useRouter();
const route = useRoute();
const activeKey = route.params.category;

const postList = ref([]);

const userList = ref([]);

const pictureList = ref([]);

const initSearchParams = {
  text: "",
  type: activeKey,
  pageNum: 1,
  pageSize: 10,
};

const searchText = ref(route.query.text || "");

// const loadDataOld = (params: any) => {
//   const postQuery = {
//     ...params,
//     searchText: params.text,
//   };
//   myAxios.post("/post/list/page/vo", postQuery).then((res: any) => {
//     console.log(res.records);
//     postList.value = res.records;
//   });
//   const pictureQuery = {
//     ...params,
//     searchText: params.text,
//   };
//   myAxios.post("/picture/list/page/vo", pictureQuery).then((res: any) => {
//     console.log(res.records);
//     pictureList.value = res.records;
//   });
//   const userQuery = {
//     ...params,
//     userName: params.text,
//   };
//   myAxios.post("/user/list/page/vo", userQuery).then((res: any) => {
//     console.log(res.records);
//     userList.value = res.records;
//   });
// };

// const loadDataAll = (params: any) => {
//   const queryParams = {
//     ...params,
//     searchText: params.text,
//   };
//   myAxios.post("/search/all", params).then((res: any) => {
//     userList.value = res.userList;
//     postList.value = res.postList;
//     pictureList.value = res.pictureList;
//   });
// };

const loadData = (params: any) => {
  const query = {
    ...params,
    searchText: params.text,
  };
  if (query.type === "") {
    myAxios.post("/search/all", query).then((res: any) => {
      userList.value = res.userList;
      postList.value = res.postList;
      pictureList.value = res.pictureList;
      return;
    });
  }
  myAxios.post("/search/type", query).then((res: any) => {
    console.log(res);
    if (query.type === "post") {
      postList.value = res.dataList;
    } else if (query.type === "user") {
      userList.value = res.dataList;
    } else if (query.type === "picture") {
      pictureList.value = res.dataList;
    }
  });
};

const searchParams = ref(initSearchParams);
// 首次请求
// loadData(searchParams);

watchEffect(() => {
  searchParams.value = {
    ...initSearchParams,
    text: route.query.text,
    type: route.params.catagory,
  } as any;
  loadData(searchParams.value);
});

const onSearch = (value: string) => {
  router.push({
    query: {
      ...searchParams,
      text: value,
    } as any,
  });
};

const onTabChange = (key: string) => {
  router.push({
    path: `/${key}`,
    query: searchParams.value,
  });
};
</script>
