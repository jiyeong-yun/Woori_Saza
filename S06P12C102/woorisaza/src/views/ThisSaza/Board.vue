<template>
        <div>
            <!-- 카테고리 + 검색 -->
            <div>    
                <b-row>       
                    <b-col cols="8">
                        <div>
                            <b-form-select v-model="category" :options="categories" size="sm" style="width:48%" @change="searchByOption"></b-form-select>&nbsp;
                            <b-form-select v-model="area" :options="areas" size="sm" style="width:48%" @change="searchByOption"></b-form-select>
                        </div>
                    </b-col>
                    <b-col cols="4">
                        <div>
                            <b-form-input v-model="search" placeholder="검색어를 입력하세요." size="sm" style="width:75%;display:inline" @change="searchByKeyword"></b-form-input>
                            <b-icon icon="search" style="width:25%;cursor:pointer;"></b-icon>
                        </div>
                    </b-col>    
                    </b-row>  
            </div>
            
            <!-- 게시글 목록 -->
            <div class="mt-3">
                <div v-for="(item, index) in printedArticleNo" :key="index">
                    <Article :articleNo="item"></Article>
                    <hr>
                </div>
            </div>
            <infinite-loading @infinite="infiniteHandler" slot="append" force-use-infinite-wrapper=".el-table__body-wrapper"></infinite-loading>
        </div>
</template>

<script>
import Article from "@/components/ThisSaza/Article.vue";
import InfiniteLoading from "vue-infinite-loading";
import {axios_contact} from "@/common.js"

export default {
    name: 'Board',
    components : {
        Article,
        InfiniteLoading,
    },
    data() {
        return {
            profileId : this.$cookie.get("id"),
            category : null,
            categories : [
                {value:null, text:"전체 카테고리"},
                {value:"FOOD", text:"식품"},
                {value:"LIFETHINGS", text:"생활 용품"},
                {value:"FASHION", text:"패션 잡화"},
                {value:"ELECTRICITY", text:"전자 제품"},
                {value:"DELIVERY", text:"배달"},
                {value:"ETC", text:"기타"}
            ],
            area : null,
            areas : [
                {value:null, text:"전체 지역범위"},
                {value:400, text:"걸어서 5분 이내"},
                {value:800, text:"걸어서 10분 이내"},
                {value:2000, text:"차타고 5분 이내"},
                {value:4000, text:"차타고 10분 이내"}
            ],
            search:null,
            scrollPostion : 0,
            printedArticleNo:[],
            articleNo:[
            ],
        };
    },
    created() {
        axios_contact({
            method : "get",
            url : "/article?profileId="+this.profileId,
        }).then(({data})=>{
            // console.log(data)
            for (let index = 0; index < data.articleList.length; index++) {
                // console.log(data.articleList[index])                
                this.articleNo.push(data.articleList[index].id)
            }

            // for (let index = 0; index < 10; index++) {
            //     this.printedArticleNo.push(this.articleNo[index])
            // }
        })
    },
    mounted() {
        
    },

    methods: {
        infiniteHandler($state){
            // console.log($state);
            const that = this;
            setTimeout(function() {
                if(that.printedArticleNo.length < that.articleNo.length){
                    let len = that.printedArticleNo.length;
                    let length = (len+10 < that.articleNo.length) ? len+10 : that.articleNo.length; 
                    for (let index = len; index < length; index++) {
                        that.printedArticleNo.push(that.articleNo[index])
                    }
                    // console.log(this.printedArticleNo.length)
                    $state.loaded();
                }
                else{
                    $state.complete();
                }
            }, 1000);
        },

        searchByOption(){
            let urlOption = "/article?profileId="+this.profileId;
            if(this.category !=null){
                urlOption += "&category="+this.category;
            }
            if(this.area !=null){
                urlOption += "&range="+this.area;
            }
            // console.log(urlOption)
            this.printedArticleNo=[];
            this.articleNo=[];
            
            axios_contact({
                method : "get",
                url : urlOption,
            }).then(({data})=>{
                // console.log(data)
                for (let index = 0; index < data.articleList.length; index++) {
                    // console.log(data.articleList[index])                
                    this.articleNo.push(data.articleList[index].id)
                }

                let length = (this.articleNo.length < 10) ?  this.articleNo.length : 10;
                for (let index = 0; index < length; index++) {
                    this.printedArticleNo.push(this.articleNo[index])
                }
            })
        },

        searchByKeyword(data){
            console.log(data)
            let urlOption = "/article?profileId="+this.profileId;
            if(this.category !=null){
                urlOption += "&category="+this.category;
            }
            if(this.area !=null){
                urlOption += "&range="+this.area;
            }
             urlOption += "&keyword="+data;
            // console.log(urlOption)
            this.printedArticleNo=[];
            this.articleNo=[];
            
            axios_contact({
                method : "get",
                url : urlOption,
            }).then(({data})=>{
                // console.log(data)
                for (let index = 0; index < data.articleList.length; index++) {
                    // console.log(data.articleList[index])                
                    this.articleNo.push(data.articleList[index].id)
                }

                let length = (this.articleNo.length < 10) ?  this.articleNo.length : 10;
                for (let index = 0; index < length; index++) {
                    this.printedArticleNo.push(this.articleNo[index])
                }
            })
        },
    },
};
</script>

<style scoped>
    .custom-select{
        font-size: 0.5em;
        
    }

    .form-control{
        font-size: 0.5em;
    }
</style>