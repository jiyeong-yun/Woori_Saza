// const ogs = require('open-graph-scraper');
// const options = { url: 'http://ogp.me/' };

// ogs(options, (error, results, response) => {
//   console.log('error:', error); // This returns true or false. True if there was an error. The error itself is inside the results object.
//   console.log('results:', results); // This contains all of the Open Graph results
//   console.log('response:', response); // This contains the HTML of page
// });

// export {ogs}
// // module.exports={
    
// // };

import _ogs from "open-graph-scraper";

export const ogs = ({url:url}) => {
    _ogs({ url })
}