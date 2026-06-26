import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResumeDetails } from './resume-details';

describe('ResumeDetails', () => {
  let component: ResumeDetails;
  let fixture: ComponentFixture<ResumeDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResumeDetails],
    }).compileComponents();

    fixture = TestBed.createComponent(ResumeDetails);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
