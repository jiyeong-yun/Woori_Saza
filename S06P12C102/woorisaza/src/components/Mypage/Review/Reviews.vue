<template>
    <div>
        <!-- 내가 작성한 후기 -->
        <div v-if="reviewList.length == 0" style="text-align:center">
            아직 작성한 후기가 없습니다. 거래를 한적이 있다면 후기를 남겨보세요!
        </div>
        <div v-if="reviewList.length > 0">
            <div v-for="(item, index) in reviewList" :key="index">
                <Review :review="item"></Review>
            </div>
        </div>
    </div>
</template>

<script>
import Review from "@/components/Mypage/Review/Review.vue";
import {axios_contact} from "@/common.js"

export default {
    name: 'Reviews',
    components:{
        Review,
    },
    data() {
        return {
            reviewList:[],
            id : this.$cookie.get('id'),
        };
    },

    created() {
        axios_contact({
            method : "get",
            url : "/review/to/"+this.id,
        }).then(({data})=>{
            console.log(data)
           this.reviewList = data.reviewList;
        })
    },

    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style lang="scss" scoped>

</style>