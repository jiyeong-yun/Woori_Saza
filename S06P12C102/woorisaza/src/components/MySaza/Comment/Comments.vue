<template>
    <div>
        <!-- 내가 작성한 댓글 -->
        <div v-if="comments.length == 0" style="text-align:center">
            작성한 댓글이 없습니다.
        </div>
        <div v-if="comments.length > 0">
            <div v-for="(item, index) in comments" :key="index">
                <Comment :comment="item"></Comment>
            </div>
        </div>
    </div>
</template>

<script>
import Comment from "@/components/MySaza/Comment/Comment.vue";
import {axios_contact} from "@/common.js"

export default {
    name: 'Comments',
    components:{
        Comment,
    },
    data() {
        return {
            comments:[],
            id : this.$route.params.id
        };
    },

    created() {
        axios_contact({
            method : "get",
            url : "/comment/"+this.id,
        }).then(({data})=>{
            for (let index = 0; index < data.myCommentList.length; index++) {
                this.comments.push(data.myCommentList[index])
            }
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