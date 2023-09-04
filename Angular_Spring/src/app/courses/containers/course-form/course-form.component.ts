import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { CoursesService } from '../../services/courses.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common'
import { Course } from '../../model/course';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit{

  form = this.formBuilder.group({
    _id: [''],
    nome: ['',[Validators.required,
      Validators.minLength(5),
      Validators.maxLength(100)]],
    categoria: ['',Validators.required],
    //categoria: new FormControl('',{nonNullable: true}),
  });

  constructor(
    //private formBuilder: FormBuilder,
    private service: CoursesService,
    private snackBar: MatSnackBar,
    private formBuilder: NonNullableFormBuilder, //não deixa null no form
    private location: Location, //location do angular
    private route: ActivatedRoute,
  ){}

  ngOnInit(): void {
    const course: Course = this.route.snapshot.data['course'];
    this.form.setValue({
      _id: course._id,
      nome: course.nome,
      categoria: course.categoria,
    })
  }

  onSubmit(){
    this.service.save(this.form.value)
    .subscribe(
      result => this.onSuccess(),
      error => this.onError()
    );
  }
  onCancel(){
    this.location.back();
  }

  private onError(){
    this.snackBar.open("Erro ao salvar curso!",'',{duration: 3000});
  }
  private onSuccess(){
    this.snackBar.open("Curso salvo!",'',{duration: 3000});
    this.onCancel();
  }

  getErrorMessage(fieldNome: string){
    const field = this.form.get(fieldNome);
    if(field?.hasError('required')){
      return 'Campo obrigatorio!';
    }
    if(field?.hasError('minlength')){
      const requiredLength = field.errors ? field.errors['minLength']['requiredLength'] : 3;
      return `Tamanho minimo precisa ser de ${requiredLength} caracteres.`;
    }
    if(field?.hasError('maxlength')){
      const requiredLength = field.errors ? field.errors['maxLength']['requiredLength'] : 100;
      return `Tamanho maximo excedido de ${requiredLength} caracteres.`;
    }
    return 'Error 404!';
  }

}
