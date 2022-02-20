import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Guide from "../views/Guide.vue"
import MySaza from "../views/MySaza/MySaza.vue";
import MyPage from "../views/MySaza/MyPage.vue";
import MyQuestion from "../views/MySaza/MyQuestion.vue";
import MyAnswer from "../views/MySaza/MyAnswer.vue";

import Board from "../views/ThisSaza/ThisSaza.vue";
import ArticleDetail from "../views/ThisSaza/ThisSaza.vue";
import ArticleWrite from "../views/ThisSaza/ThisSaza.vue";
import ArticleUpdate from "../views/ThisSaza/ArticleUpdate.vue";
import Request from "../views/Request/Request.vue";

import PartyDetail from "../views/PartyDetail/PartyDetail.vue";
import Review from "../views/PartyDetail/Review.vue";
import PaidForm from "../views/PartyDetail/PaidForm.vue";
import Register from "../views/Register.vue";
import Admin from "../views/Admin.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
  },
  {
    path: "/guide",
    name: "Guide",
    component: Guide,
  },
  {
    path: "/admin",
    name: "Admin",
    component: Admin,
  },
  {
    path: "/board",
    name: "Board",
    component: Board,
  },
  {
    path: "/board/:articleno",
    name: "ArticleDetail",
    component: ArticleDetail,
  },
  {
    path: "/board/update/:articleno",
    name: "ArticleUpdate",
    component: ArticleUpdate,
  },
  {
    path: "/board/write",
    name: "ArticleWrite",
    component: ArticleWrite,
  },
  {
    path: "/request/:articleNo",
    name: "Request",
    component: Request,
  },
  {
    path: "/mysaza/:id",
    name: "MySaza",
    component: MySaza,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
  },
  {
    path: "/mypage/question/:qnaid",
    name: "MyQuestion",
    component: MyQuestion,
  },
  {
    path: "/mypage/answer/:qnaid",
    name: "MyAnswer",
    component: MyAnswer,
  },
  {
    path: "/partydetail/:partyId",
    name: "PartyDetail",
    component: PartyDetail,
  },
  {
    path: "/review/:partyId",
    name: "Review",
    component: Review,
  },
  {
    path: "/paidform/:partyId/:bm",
    name: "PaidForm",
    component: PaidForm,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
