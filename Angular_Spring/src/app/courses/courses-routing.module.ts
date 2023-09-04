import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CoursesComponent } from './containers/courses/courses.component';
import { CourseFormComponent } from './containers/course-form/course-form.component';
import { courseResolve } from './guards/course.resolver';

const routes: Routes = [
  {path: '', component: CoursesComponent},
  {path: 'new', component: CourseFormComponent,  resolve: {course: courseResolve}},
  {path: 'edit/:id', component: CourseFormComponent, resolve: {course: courseResolve}},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoursesRoutingModule { }
