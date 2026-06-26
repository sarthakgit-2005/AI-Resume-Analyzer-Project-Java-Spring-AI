import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AiResults } from './ai-results';

describe('AiResults', () => {
  let component: AiResults;
  let fixture: ComponentFixture<AiResults>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AiResults],
    }).compileComponents();

    fixture = TestBed.createComponent(AiResults);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
