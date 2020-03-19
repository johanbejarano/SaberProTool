import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearPruebaComponent } from './crear-prueba.component';

describe('CrearPruebaComponent', () => {
  let component: CrearPruebaComponent;
  let fixture: ComponentFixture<CrearPruebaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrearPruebaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrearPruebaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
