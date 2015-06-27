var gulp = require('gulp');
// 16. brower-sync
var browserSync = require('browser-sync').create();
var reload      = browserSync.reload;
// Static server
gulp.task('browser-sync', function() {
    browserSync.init({
        server: {
            baseDir: "./"
        }
    });
    gulp.watch(["./assets/css/*.css", "!./**/*min.css"]).on('change', reload);
    gulp.watch("./*.html").on('change', reload);
});


var rename = require("gulp-rename");


var sourcemaps = require('gulp-sourcemaps');
gulp.task('sourcemapsTest', function() {
  gulp.src('./concatJs/*.js')
    .pipe(sourcemaps.init())
      .pipe(concat('daehanmingukmanseVersion2.js'))
      .pipe(uglify())
      .pipe(rename('daehanmingukmanse.min.js'))
    .pipe(sourcemaps.write())
    .pipe(gulp.dest('dist'));
});


// 04. CSS 최소화
var ext_replace = require('gulp-ext-replace');
var minifyCss = require('gulp-minify-css');
gulp.task('minify-css', function() {
  return gulp.src(["static/vendor/bootstrap/dist/css/*.css", "!./**/*min.css"])
  	.pipe(sourcemaps.init())
    .pipe(minifyCss({compatibility: 'ie8'}))
    .pipe(rename(function (path){
    	path.dirname += "/static/vendor/bootstrap/dist/css";
      path.basename += ".min";
    	path.extname = ".css"
    }))
    .pipe(sourcemaps.write())
    .pipe(gulp.dest('.'));
});

gulp.task('minify-cssSync', function() {
  return gulp.src(["static/vendor/bootstrap/dist/css/*.css", "!./**/*min.css"])
    .pipe(sourcemaps.init())
    .pipe(minifyCss({compatibility: 'ie8'}))
    .pipe(rename(function (path){
      path.dirname += "/static/vendor/bootstrap/dist/css";
      path.basename += ".min";
      path.extname = ".css"
    }))
    .pipe(sourcemaps.write())
    .pipe(gulp.dest('.'))
    .pipe(browserSync.stream({match: '**/*.css'}));
});


//15. watch를 통한 실시간 변경
gulp.task('watch', function(){

  //css폴더의 scss파일들이 변경되면 sass태스크 실행
  gulp.watch(["static/vendor/bootstrap/dist/css/*.css", "!./**/*min.css"], ['minify-css']);
});


gulp.task('default', ['browser-sync']);