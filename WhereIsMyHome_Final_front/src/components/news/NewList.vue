<template>
  <div class="">
    <table class="table table-striped hover">
      <caption>
        네이버 부동산 뉴스
      </caption>
      <thead>
        <tr>
          <th>뉴스 제목</th>
          <th>뉴스 내용</th>
          <th>작성일</th>
        </tr>
      </thead>
      <tr v-for="(news, index) in items" :key="index">
        <td>
          <a :href="news.originallink" target="_blank" v-html="news.title"></a>
        </td>
        <td v-html="news.description"></td>
        <td>
          {{ news.pubDate }}
        </td>
      </tr>
      <!-- <tr striped hover :items="items" :fields="fields"></tr> -->
    </table>
  </div>
</template>

<script>
import http from "@/api/lib/http";
export default {
  name: "NewsList",
  data() {
    return {
      news: "",
      items: [],
      fields: [
        { key: "title", label: "뉴스 제목", tdClass: "tdClass" },
        { key: "description", label: "뉴스 내용", tdClass: "tdSubject" },
        { key: "pubDate", label: "뉴스 작성일", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    http.get(`/news`).then(({ data }) => {
      this.news = data;
      // console.log(this.news.items);
      this.items = this.news.items;
      console.log(this.items[0]);
    });
  },
};
</script>

<style></style>
