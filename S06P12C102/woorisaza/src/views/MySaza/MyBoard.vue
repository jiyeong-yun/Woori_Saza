<template>
        <div>
            <!-- 게시글 목록 -->
            <div class="mt-3">
                <div v-for="(item, index) in articleNo" :key="index">
                    <Article :articleNo="item"></Article>
                    <hr>
                </div>
            </div>
            
        </div>
</template>

<script>
import Article from "@/components/MySaza/Article.vue";
export default {
    name: 'MyBoard',
    components : {
        Article,
    },
    data() {
        return {
            articleNo:[1,2,3,4,5,6,7,8,9,10],
        };
    },

    mounted() {
        
    },

    methods: {
        
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