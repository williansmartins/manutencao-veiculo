var gulp        = require('gulp');
var browserSync = require('browser-sync').create();
gulp.task('browser-sync', function() {
    browserSync.init({
        server: {
            baseDir: "./"
        }
    });
});
// gulp.task('browser-sync', function() {
//     browserSync.init({
//         open: false,
//         injectChanges: true,
//         proxy: "https://localhost.dev/"
//     });
// });